package com.act.common.biz.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by steven on 2019/6/24.
 */
@Configuration
@ConditionalOnProperty(value = "swagger.enable",matchIfMissing = false)
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(new ApiInfoBuilder()
            //页面标题
            .title("api接口测试")
            //创建人
            .contact(new Contact("xxxx","",""))
            //版本号
            .version("1.0")
            .build())
        .select()
        // 设置需要扫描的 API 包路径
        .apis(RequestHandlerSelectors.basePackage("com.act"))
        .paths(PathSelectors.any())
        .build();
  }

}
