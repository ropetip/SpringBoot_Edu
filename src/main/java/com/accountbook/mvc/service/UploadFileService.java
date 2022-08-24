package com.accountbook.mvc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.mvc.parameter.UploadFileParameter;
import com.accountbook.mvc.repository.UploadFileRepository;

/**
 * 업로드 파일 서비스
 * @author jlee
 */
@Service
public class UploadFileService {
	
	@Autowired
	private UploadFileRepository repository;
	
	/**
	 * 등록처리
	 */
	public void save(UploadFileParameter param) {
		repository.save(param);
	}
}
