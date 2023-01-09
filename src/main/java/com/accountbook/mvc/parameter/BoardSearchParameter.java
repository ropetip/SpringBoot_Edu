package com.accountbook.mvc.parameter;


import java.util.List;

import com.accountbook.mvc.domain.BoardType;

import lombok.Data;

/**
 * 게시물 검색
 * @author walma
 *
 */
@Data
public class BoardSearchParameter {
	private String keyword;
	private BoardType boardType;
	private List<BoardType> boardTypes;
}
