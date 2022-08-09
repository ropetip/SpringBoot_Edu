package com.accountbook.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accountbook.config.GlobalConfig;
import com.accountbook.config.http.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig config;


	/**
	 * 업로드 리턴.
	 * @return
	 */

	@PostMapping
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save() {
		logger.info("config : {}", config);
		String uploadFilePath = config.getUploadFilePath();
		logger.info("uploadFilePath : {}", uploadFilePath);
		return new BaseResponse<Boolean>(true);
	}

}
