package com.zzq.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzq.annotation.SubDatabaseTable;
import com.zzq.dynamic.dataSource.DynamicDataConfig;
import com.zzq.dynamic.dataSource.DynamicDataSource;
import com.zzq.dynamic.dataSource.DynamicDataSourceContextHolder;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.*;

/**
 * 分表拦截器
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SubTableInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(SubTableInterceptor.class);
    @Autowired
    DynamicDataConfig dynamicDataConfig;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
        //优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，
        //同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        MetaObject metaObject = MetaObject
                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                        new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();

        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        //数据库连接信息
        Configuration configuration = mappedStatement.getConfiguration();
       // DynamicDataSource dataSource = (DynamicDataSource) configuration.getEnvironment().getDataSource();

        //System.out.println(DynamicDataSourceContextHolder.getDataSourceType());


        Class<?> classType = Class.forName(mappedStatement.getId().substring(0,mappedStatement.getId().lastIndexOf(".")));
        String mName  = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1 ,mappedStatement.getId().length());
        //此处获取ID 用于确定库 表
        long ID=0L;
        try {
            if(boundSql.getParameterObject() instanceof  Long){
                ID= (Long) boundSql.getParameterObject();
            }else if(boundSql.getParameterObject() instanceof Map){
                ID = (Long) ((Map) boundSql.getParameterObject()).get("id");
            }else if(boundSql.getParameterObject() instanceof Object){
                ObjectMapper mapper = new ObjectMapper();
                String json= mapper.writeValueAsString(boundSql.getParameterObject());
                Map <String,Object> map = mapper.readValue(json,Map.class);
                ID= (long) map.get("id");
            }
        }catch (Exception e){
            throw new RuntimeException("【请遵从约定优于配置】"+e.getMessage());
        }

        String schem="";
        for(Method method: classType.getDeclaredMethods()){
            if(method.isAnnotationPresent(SubDatabaseTable.class)&&method.getName().equals(mName)){
                SubDatabaseTable subDatabaseTable= method.getAnnotation(SubDatabaseTable.class);
                String dataBase=subDatabaseTable.dataBaseName();
                String tablename=subDatabaseTable.tableName();
                long dataBaseIndex= ID%dynamicDataConfig.sub_database_num+1;
                long tableNameIndex=ID%dynamicDataConfig.sub_table_num+1;
                schem=dataBase+dataBaseIndex+"."+tablename+"_"+tableNameIndex;
                break;
            }
        }

        //通过反射改写sql
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        String mSql = MessageFormat.format(boundSql.getSql(), schem);
        field.set(boundSql, mSql);
        Object returnVal = invocation.proceed();

        return returnVal;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }

    /**
     * 获取SQL
     *
     * @param configuration
     * @param boundSql
     * @return
     */
    private String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterObject == null || parameterMappings.size() == 0) {
            return sql;
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        return sql;
    }

    private String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}
