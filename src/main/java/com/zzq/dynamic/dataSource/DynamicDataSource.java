package com.zzq.dynamic.dataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sound.midi.Soundbank;

/**
 * 动态数据源
 * AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private Logger logger = Logger.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("当前数据库源："+DynamicDataSourceContextHolder.getDataSourceType());
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
