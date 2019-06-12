package com.zzq;

import com.zzq.dynamic.dataSource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("com.zzq")
@Import(DynamicDataSourceRegister.class)
public class BootStart
{
    public static void main( String[] args )
    {
        SpringApplication.run(BootStart.class,args);
    }
}
