# dynamicDataSource
SpringBoot+Mybatis 分库分表demo
### 约定优于配置
#### 约定说明
1.所有实体Bean 必须有一个Long类型id 不允许数据库自增。

2.在AOP处确定数据源，service层方法第一个参数必须是id

2.在Mybatis拦截器中动态确定库，表。mapper方法参数有三种方式 
    
    1.例如save方法，只需要传入实体bean即可。
   
    2.有时只需要主键，就可以完成操作，只需传入id即可。
    
    3.当有多个参数时，第一个参数必须为id
    
3.数据源在application.properties中配置

4.sub.properties 配置分库数、分表数。详情看文件内注解

#### 多数据源约定说明
```properties
#所有数据源别名 多个数据源用','隔开
slave.datasource.names=d1,d2,d3
#多数据源数据库配置 需使用别名区分 url无需指定库名
slave.datasource.d1.url=jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=utf8
slave.datasource.d1.driver=com.mysql.jdbc.Driver
slave.datasource.d1.username=xxx
slave.datasource.d1.password=xxx

slave.datasource.d2.url=jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=utf8
slave.datasource.d2.driver=com.mysql.jdbc.Driver
slave.datasource.d2.username=xxx
slave.datasource.d2.password=xxx

slave.datasource.d3.url=jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=utf8
slave.datasource.d3.driver=com.mysql.jdbc.Driver
slave.datasource.d3.username=xxx
slave.datasource.d3.password=xxx

#数据源类型 本处使用DruidDataSource
dynamic.datasource.type=com.alibaba.druid.pool.DruidDataSource
```
#### 建库 建表 命名规则
    库名规则
    [databaseName][index] eg:userInfo1,userInfo2,userInfo3
    表名规则
    [tableName]_[index]   eg:user_1,user_2,user_3

#### @SubDatabaseTable注解说明
        @SubDatabaseTable 使用在mapper接口方法上
        dataBaseName： mapper方法需要执行的目标数据库(eg:userInfo 不明确指定某个库)
        tableName：    mapper方法需要执行的目标表(eg:user 不明确指定某个表)
```java
    @SubDatabaseTable(dataBaseName = "userInfo",tableName = "user")
    int insertSelective(User record);
```
    
    
