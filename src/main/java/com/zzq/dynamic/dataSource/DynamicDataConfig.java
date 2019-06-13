package com.zzq.dynamic.dataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:sub.properties")
public class DynamicDataConfig {
    @Value("sub.database.num")
    public int sub_database_num;
    @Value("sub.table.num")
    public int sub_table_num;
}
