package com.vam.mapper;

import java.util.List;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;

public interface BoardMapper {

	// 게시판 등록
	public void enroll(BoardVO board);
	
	// 게시판 목록 // select의 결과가 한개 이상이라서 list로 받는
	public List<BoardVO> getList();
	// 컬렉션 프레임웍 // 제너릭
	
	// 게시판 목록(페이징 적용)
	public List<BoardVO> getListPaging(Criteria cri);

	// 게시판 조회
	public BoardVO getPage(int bno);
	
	// 게시판 수정
	public int modify(BoardVO board); // 수정 성공 1, 실패 0 return
	
	// 게시판 삭제
	public int delete(int bno);
}
