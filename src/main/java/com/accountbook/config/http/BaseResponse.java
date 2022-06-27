package com.accountbook.config.http;

import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스 
 * @author jlee
 * @param <T>
 */
@Data
public class BaseResponse<T> {
	private BaseResponseCode code;
	private String message;
	private T data;
	
	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}
}
