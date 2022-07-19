package com.accountbook.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountbook.mvc.domain.Board;
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
    
	/**
	 * 단순 반복문을 이용한 등록 처리
	 */
	public void saveList1(List<BoardParameter> list) {
		for(BoardParameter param : list) {
			repository.insert(param);
		}
	}
	
	/**
	 * 100개씩 배열에 담아서 일괄 등록 처리
	 */
	public void saveList2(List<BoardParameter> boardList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardList", boardList);
		repository.saveList(paramMap);
	}
}
