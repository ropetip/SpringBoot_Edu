package com.accountbook.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.config.http.BaseResponse;
import com.accountbook.mvc.dao.Board;
import com.accountbook.mvc.parameter.BoardParameter;
import com.accountbook.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@Api(tags = "게시판API")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	@ApiOperation(value="목록 조회", notes=" 게시물 목록 정보를 조회할 수 있습니다.")	
	public BaseResponse<List<Board>> getList() {
		return new BaseResponse<List<Board>>(boardService.getList());
	}
	
	@GetMapping("/{boardSeq}")
	@ApiOperation(value="상세 조회", notes=" 게시물 번호에 해당하는 상세정보를 조회할 수 있습니다.")	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value="게시물 번호", example = "1")
	})
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}
	
	@PutMapping
	@ApiOperation(value="등록/수정 처리", notes=" 신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value="게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title", value="제목", example = "spring"),
		@ApiImplicitParam(name = "contents", value="내용", example = "spring 강좌"),
	})
	public BaseResponse<Integer> save(BoardParameter param) {
		boardService.save(param);
		return new BaseResponse<Integer>(param.getBoardSeq()); 
	}
	
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value="삭제 처리", notes=" 게시물 번호에 해당하는 정보를 삭제합니다.")	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value="게시물 번호", example = "1"),
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return new BaseResponse<Boolean>(false); 
		} 
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
	
}