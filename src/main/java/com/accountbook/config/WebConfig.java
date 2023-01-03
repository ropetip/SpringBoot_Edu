package com.accountbook.config;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.accountbook.config.servlet.handler.BaseHandlerInterCeptor;
import com.accountbook.framework.data.web.MySQLPageRequestHandleMethodArgumentResolver;
import com.accountbook.mvc.domain.BaseCodeLabelEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
	@Bean
	public BaseHandlerInterCeptor baseHandlerInterCeptor() {
		return new BaseHandlerInterCeptor();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}
	
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
		jsonView.setObjectMapper(objectMapper());
		return jsonView;
	}
	
	@Bean
	public GlobalConfig config() {
		return new GlobalConfig();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(baseHandlerInterCeptor());
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// 페이지 리졸버 등록
		resolvers.add(new MySQLPageRequestHandleMethodArgumentResolver());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		GlobalConfig config = this.config();
		// 업로드 파일 static resource 접근 경로
		String resourcePattern = config.getUploadResourcePath() + "**";
		// 로컬(윈도우 환경)
		if(config.isLocal()) {
			registry.addResourceHandler(resourcePattern).addResourceLocations("file:///" + config.getUploadFilePath());
		} else {
			// 리눅스 또는 윈도우 환경
			registry.addResourceHandler(resourcePattern).addResourceLocations("file:" + config.getUploadFilePath());
		}
	}
	
	@Bean
	public FilterRegistrationBean<SitemeshConfig> sitemeshBean() {
		FilterRegistrationBean<SitemeshConfig> filter = new FilterRegistrationBean<SitemeshConfig>();
		filter.setFilter(new SitemeshConfig());
		return filter;
	}
}
