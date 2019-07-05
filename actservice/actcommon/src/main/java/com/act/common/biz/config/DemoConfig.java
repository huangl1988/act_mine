package com.act.common.biz.config;

import com.act.common.biz.util.DemoUserHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by steven on 2019/6/25.
 */
@Configuration
@ConditionalOnProperty(value = "mock.enable",matchIfMissing = false)
public class DemoConfig {

  @Bean
  public DemoUserHandler demoUserHandler(){
    return DemoUserHandler.builder().build();
  }

}
