package com.accountbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정
 * @author walma
 */
@Configuration
@EnableSwagger2
@Api(tags = "게시판")
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();
		apiInfo.title("API 서버문서");
		apiInfo.description("API 서버문서입니다.");
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo.build());
		
		ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("com.accountbook.mvc.controller"));
		apis.paths(PathSelectors.ant("/**"));
		
		return apis.build();
	}
}
