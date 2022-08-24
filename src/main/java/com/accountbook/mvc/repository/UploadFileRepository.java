package com.accountbook.mvc.repository;


import org.springframework.stereotype.Repository;

import com.accountbook.mvc.parameter.UploadFileParameter;

/**
 * 업로드 파일 Repository
 * @author jlee
 *
 */
@Repository
public interface UploadFileRepository {
	
	void save(UploadFileParameter param);
	
}
