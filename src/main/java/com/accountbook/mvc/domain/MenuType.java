package com.accountbook.mvc.domain;
/**
 * 게시판 종류
 * @author walma
 *
 */
public enum MenuType{ 
	community(BoardType.COMMUNITY),
	notice(BoardType.NOTICE),
	faq(BoardType.FAQ),
	inquiry(BoardType.INQUIRY),
	;
	
	private BoardType boardType;
	
	MenuType(BoardType boardType) {
		this.boardType = boardType;
	}
	
}
