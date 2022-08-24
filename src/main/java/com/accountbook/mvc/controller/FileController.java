package com.accountbook.mvc.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accountbook.config.GlobalConfig;
import com.accountbook.config.exception.BaseException;
import com.accountbook.config.http.BaseResponse;
import com.accountbook.config.http.BaseResponseCode;
import com.accountbook.mvc.parameter.UploadFileParameter;
import com.accountbook.mvc.service.UploadFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig config;

	@Autowired
	private UploadFileService uploadFileService;

	/**
	 * 업로드 리턴.
	 * @return
	 */

	@PostMapping("/save")
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.info("multipartFile : {}", multipartFile);
		if(multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
		}
		// 날짜폴더를 추가
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String uploadFilePath = config.getUploadFilePath() + currentDate + "/";
		logger.debug("uploadFilePath : {}", uploadFilePath);
		
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1);
		String filename = UUID.randomUUID().toString() + "." + prefix;
		
		File folder = new File(uploadFilePath);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		String pathname = uploadFilePath + filename;
		String resourcePathname = config.getUploadResourcePath() + currentDate + "/" + filename;
		logger.info("pathname : {}", pathname);
		logger.info("resourcePathname : {}", resourcePathname);
		
		File dest = new File(pathname);
		try {
			multipartFile.transferTo(dest);
			// 파일업로드 된 후 DB에 저장
			UploadFileParameter param = new UploadFileParameter();
			// 컨텐츠 종류
			param.setContentType(multipartFile.getContentType());
			param.setOriginalFilename(multipartFile.getOriginalFilename());
			param.setFilename(filename);
			param.setPathname(pathname);
			param.setSize((int)multipartFile.getSize());
			// static resource 접근경로
			param.setResourcePathname(resourcePathname);
			uploadFileService.save(param);
		} catch (Exception e) {
			logger.error("e", e);
		}

		return new BaseResponse<Boolean>(true);
	}

}
