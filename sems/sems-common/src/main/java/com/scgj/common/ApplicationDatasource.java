package com.scgj.common;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.datasource")
public class ApplicationDatasource extends AbstractDatasource {

    /** The Constant NAME. */
    public static final String NAME = "applicationDataSource";

    /**
     * Ots data source.
     *
     * @return the pool data source
     */
    @Bean(name=NAME)
    @Primary
    public DataSource applicationDataSource() {
        return super.configureDataSource();
    }
} 
