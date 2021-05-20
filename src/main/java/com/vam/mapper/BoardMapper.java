package com.vam.mapper;

import java.util.List;

import com.vam.model.BoardVO;

public interface BoardMapper {

	// 게시판 등록
	public void enroll(BoardVO board);
	
	// 게시판 등록 // select의 결과가 한개 이상이라서 list로 받는
	public List<BoardVO> getList();
	// 컬렉션 프레임웍 // 제너릭
}
