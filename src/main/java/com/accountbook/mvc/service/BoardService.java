package com.accountbook.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.mvc.dao.Board;
import com.accountbook.mvc.parameter.BoardParameter;
import com.accountbook.mvc.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	public List<Board> getList() {
		return repository.getList();	
	}
	
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	public void save(BoardParameter param) {
		Board board = repository.get(param.getBoardSeq());
		if(board == null) {
			repository.insert(param);	
		} else {
			repository.update(param);
		}
	}

	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
    
}
