package com.accountbook.config.http;

public enum BaseResponseCode {
	SUCCESS, // 성공
	ERROR, // 에러
	LOGIN_REQUIRED, // 로그인 필수
	DATA_IS_NULL, // 널
	VALIDATE_REQUIRED // 필수체크
}
