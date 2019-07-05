package com.act.datasource.conf;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import java.util.Properties;
import javax.security.auth.login.ConfigurationSpi;
import javax.sql.DataSource;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by steven on 2019/6/24.
 */
@Configuration
public class MybatisPlusConfig {


  private DbConfig dbConfig(){
    DbConfig dbConfig = new DbConfig();
    dbConfig.setTablePrefix("tb");
    return dbConfig;
  }

  private GlobalConfig globalConfig(){
    GlobalConfig globalConfig=new GlobalConfig();
    globalConfig.setDbConfig(dbConfig());
    return globalConfig;
  }



  private MybatisConfiguration mybatisConfiguration(){
    MybatisConfiguration mybatisConfiguration=new MybatisConfiguration();
    mybatisConfiguration.setAggressiveLazyLoading(false);
    mybatisConfiguration.setLazyLoadingEnabled(true);
    mybatisConfiguration.setAutoMappingUnknownColumnBehavior(
        AutoMappingUnknownColumnBehavior.FAILING);
    mybatisConfiguration.setCacheEnabled(false);
    mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);

    return mybatisConfiguration;
  }

  @Bean(name="actMybatisSqlSessionFactoryConfiguration")
  public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource){
    MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration());
    mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig());
    mybatisSqlSessionFactoryBean.setTypeAliasesPackage("com.act.**.mapper");
    mybatisSqlSessionFactoryBean.setDataSource(dataSource);

    return mybatisSqlSessionFactoryBean;
  }

  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  /**
   * 打印 sql
   */
  @Bean
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    //格式化sql语句
    Properties properties = new Properties();
    properties.setProperty("format", "true");
    performanceInterceptor.setProperties(properties);
    return performanceInterceptor;
  }


}
