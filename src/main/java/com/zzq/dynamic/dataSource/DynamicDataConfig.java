package com.zzq.dynamic.dataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:sub.properties")
@Order(1)
public class DynamicDataConfig {
    //分库数
    @Value("sub.database.num")
    public long sub_database_num;
    //分表数
    @Value("sub.table.num")
    public long sub_table_num;
    //id分表配置
    @Value("sub.table.index")
    public long sub_table_index;
}
