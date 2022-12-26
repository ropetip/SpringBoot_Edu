package com.accountbook.mvc.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/getData")
	public long getData(@RequestParam Map<String, Object> param) {
		 int num = Integer.parseInt((String)param.get("num"));
		 long d1 = new Date().getTime();
		 System.out.println("d1===>"+d1);
		 System.out.println("num===>"+num);
		 for(int i=0; i<num; i++) {
			 System.out.print(i);
		 }
		 System.out.println();
		 long d2 = new Date().getTime();
		 System.out.println("d2===>"+d2);
		 
		 long diff = d2-d1;
		 long sec = diff / 1000;

		 return sec;
	}
	
	@GetMapping("/getBtnStatus")
	public String getBtnStatus(@RequestParam Map<String, Object> param) {
		

		 return "";
	}
}
