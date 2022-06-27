package com.accountbook.config.http;

public enum BaseResponseCode {
	SUCCESS(200),  // 성공
	ERROR(500), // 실패
	;
	
	private int status;
	
	BaseResponseCode(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}
