package com.accountbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerCronConfig {
	
	@Autowired
	private GlobalConfig config;
	
	@Bean
	public String schedulerCron() {
		return config.getSchedulerCron();
	}
}
