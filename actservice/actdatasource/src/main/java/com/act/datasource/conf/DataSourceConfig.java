package com.act.datasource.conf;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by steven on 2019/6/24.
 */
@Configuration
@ConditionalOnProperty(value="mine.datasource.enable",matchIfMissing = false)
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "mine.datasource.jdbc")
  public DataSource dataSource(){
    return new HikariDataSource();
  }

}
