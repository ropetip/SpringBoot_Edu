package com.accountbook.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accountbook.config.exception.BaseException;
import com.accountbook.config.http.BaseResponse;
import com.accountbook.config.http.BaseResponseCode;
import com.accountbook.framework.data.domain.MySQLPageRequest;
import com.accountbook.framework.data.domain.PageRequestParameter;
import com.accountbook.framework.web.bind.annotation.RequestConfig;
import com.accountbook.mvc.domain.Board;
import com.accountbook.mvc.domain.MenuType;
import com.accountbook.mvc.parameter.BoardParameter;
import com.accountbook.mvc.parameter.BoardSearchParameter;
import com.accountbook.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags = "게시판API")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public String list(BoardSearchParameter param, MySQLPageRequest pageRequest, Model model) {
		logger.info("pageRequest: {}", pageRequest);
		PageRequestParameter<BoardSearchParameter> pageRequestParam = new PageRequestParameter<BoardSearchParameter>(pageRequest, param);
		List<Board> boardList = boardService.getList(pageRequestParam);
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@GetMapping("{menuType}")
	public String list(@PathVariable MenuType menuType , BoardSearchParameter param, MySQLPageRequest pageRequest, Model model) {
		logger.info("menuType: {}", menuType);
		logger.info("pageRequest: {}", pageRequest);
		PageRequestParameter<BoardSearchParameter> pageRequestParam = new PageRequestParameter<BoardSearchParameter>(pageRequest, param);
		List<Board> boardList = boardService.getList(pageRequestParam);
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@GetMapping("/form")
	public void form(Model model) {
		
	}
	
	/**
	 * 상세페이지
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/detail/{boardSeq}")
	public String detail(@PathVariable int boardSeq, Model model) {
		Board board = boardService.get(boardSeq);
		// null 처리
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물"} );
		}
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	/**
	 * 수정 화면
	 * @param param
	 * @return
	 */
	@GetMapping("/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public String edit(@PathVariable(required = true) int boardSeq, BoardParameter param, Model model) {
		Board board = boardService.get(param.getBoardSeq());
		// null 처리
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물"} );
		}
		model.addAttribute("board", board);
		model.addAttribute("param", param);
		
		return "/board/form";
	}
	
	@PostMapping("/save")
	@RequestConfig(loginCheck = false)
	@ResponseBody
	public BaseResponse<Integer> save(BoardParameter param) {
		int boardSeq = param.getBoardSeq();
		// 제목 필수 체크
		if(StringUtils.isEmpty(param.getTitle()) ) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목"} );
		}
		// 내용 필수 체크
		if(StringUtils.isEmpty(param.getContents()) ) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" } );
		}
		// boardSeq가 있는 경우 체크
		if(boardSeq != 0) {
			Board board =boardService.get(boardSeq);
			if(board == null) {
				throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "boardSeq"} );
			}
		}
		boardService.save(param);
		return new BaseResponse<Integer>(param.getBoardSeq()); 
	}
	
	@DeleteMapping("/{boardSeq}")
	@RequestConfig
	@ResponseBody
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
	
	@PutMapping("/saveList1")
	@ResponseBody
	@ApiOperation(value="대용량 등록처리1", notes="대용량 등록처리1")	
	public BaseResponse<Boolean> saveList1() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if(count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		logger.info("실행시간 : {}", (end - start) / 1000.0);
		
		return new BaseResponse<Boolean>(true);
	}
	
	@PutMapping("/saveList2")
	@ResponseBody
	@ApiOperation(value="대용량 등록처리2", notes="대용량 등록처리2")		
	public BaseResponse<Boolean> saveList2() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if(count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		logger.info("실행시간 : {}", (end - start) / 1000.0);
		
		return new BaseResponse<Boolean>(true);
	}
}
