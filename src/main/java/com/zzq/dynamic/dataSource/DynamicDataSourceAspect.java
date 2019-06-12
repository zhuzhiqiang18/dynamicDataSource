package com.zzq.dynamic.dataSource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
class DynamicDataSourceAspect {

    private Logger logger = Logger.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution(* com.zzq.service.*.*(..))")
    public void cut(){}
    //改变数据源
    @Before("cut()")
    public void changeDataSource(JoinPoint joinPoint) {
        Object[] obj= joinPoint.getArgs();
        if(obj!=null&&obj.length>0){
            if(obj[0] instanceof Long){
              long database=  Long.parseLong(obj[0].toString())%3;
              database=database==0?++database:database;
                DynamicDataSourceContextHolder.setDataSourceType("d"+database);
            }
        }
       // String dbid = targetDataSource.name();

       /* if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
           *//* //joinPoint.getSignature() ：获取连接点的方法签名对象
            logger.error("数据源 " + dbid + " 不存在使用默认的数据源 -> " + joinPoint.getSignature());*//*
        } else {
            logger.debug("使用数据源：" + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }*/
    }

    @After("cut()")
    public void clearDataSource(JoinPoint joinPoint) {
        //logger.debug("清除数据源 " + targetDataSource.name() + " !");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
