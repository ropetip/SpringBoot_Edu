package com.accountbook.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accountbook.mvc.dao.Board;
import com.accountbook.mvc.parameter.BoardParameter;

/**
 * 게시판 Repository
 * @author jlee
 *
 */
@Repository
public interface BoardRepository {
	
	List<Board> getList();
	
	Board get(int boardSeq);
	
	void insert(BoardParameter board);
	
	void update(BoardParameter board);
	
	void delete(int boardSeq);
}
