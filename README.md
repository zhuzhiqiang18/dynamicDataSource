# dynamicDataSource
SpringBoot+Mybatis 分库分表demo
### 约定优于配置
1.所有实体Bean 必须有一个Long类型id 不允许数据库自增。

2.在AOP处确定数据源，service层方法第一个参数必须是id

2.在Mybatis拦截器中动态确定库，表。mapper方法参数有三种方式 
    
    1.例如save方法，只需要传入实体bean即可。
   
    2.有时只需要主键，就可以完成操作，只需传入id即可。
    
    3.当有多个参数时，第一个参数必须为id
    
3.数据源在application.properties中配置

4.sub.properties 配置分库数、分表数。详情看文件内注解
