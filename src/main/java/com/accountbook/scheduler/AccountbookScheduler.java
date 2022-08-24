package com.accountbook.scheduler;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.accountbook.config.GlobalConfig;

import java.util.Calendar;

import org.slf4j.Logger;

@Component
public class AccountbookScheduler {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GlobalConfig globalConfig;
	
	//@Scheduled(cron ="#{@schedulerCron}")
	public void shcedule1() {
		logger.info("schedule1 동작하고 있음: {}", Calendar.getInstance().getTime());
	}
}
