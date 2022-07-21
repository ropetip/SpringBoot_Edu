package com.accountbook.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.accountbook.framework.data.domain.PageRequestParameter;
import com.accountbook.mvc.domain.Board;
import com.accountbook.mvc.parameter.BoardParameter;
import com.accountbook.mvc.parameter.BoardSearchParameter;

/**
 * 게시판 Repository
 * @author jlee
 *
 */
@Repository
public interface BoardRepository {
	
	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParam);
	
	Board get(int boardSeq);
	
	void insert(BoardParameter board);
	
	void update(BoardParameter board);
	
	void delete(int boardSeq);
	
	void saveList(Map<String, Object> paramMap);

}
