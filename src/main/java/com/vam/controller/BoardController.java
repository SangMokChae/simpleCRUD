package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;
import com.vam.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bservice;
	
	/*
	// 게시판 목록 페이지 접속
	@GetMapping("/list")
	// => @RequestMapping(value="list", method=RequestMethod.GET)
	public void boardListGET(Model model) {
		
		log.info("게시판 목록 페이지 진입");
		
		model.addAttribute("list", bservice.getList());
		
	}
	*/
	
	// 게시판 목록 페이지 접속(페이징 적용)
	@GetMapping("/list")
	public void boardListGET(Model model, Criteria cri) {
		
		log.info("boardListGET");
		
		model.addAttribute("list", bservice.getListPaging(cri));
	}
	
	// 게시판 등록 페이지 접속
	@GetMapping("/enroll")
	// => @RequestMapping(value="enroll", method=RlequestMethod.GET)
	public void boardEnrollGET() {
		
		log.info("게시판 등록 페이지 진입");
		
	}
	
	// 게시판 등록하기
	@PostMapping("/enroll")
	public String boardEnrollPost(BoardVO board, RedirectAttributes rttr) {
		
		log.info("BoardVO : " + board);
		
		bservice.enroll(board);
		
		// 일회성 전달 알림
		rttr.addFlashAttribute("result", "enrol success");
		
		// 등록, 수정, 삭제 같은 작업처리가 새로고침등으로 중복해서 들어가는 것을 막기 위해서 redirect를 한다.
		return "redirect:/board/list";
	}
	
	// 게시판 조회
	@GetMapping("/get")
	public void boardGetPageGet(int bno, Model model) {
		
		model.addAttribute("pageInfo", bservice.getPage(bno));
		
	}
	
	// 수정 페이지로 이동
	@GetMapping("/modify")
	public void boardModifyGET(int bno, Model model) {
		
		model.addAttribute("pageInfo", bservice.getPage(bno));
		
	}
	
	// 페이지 수정
	@PostMapping("/modify")
	public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {
		
		bservice.modify(board);
		
		rttr.addFlashAttribute("result", "modify success");
		
		return "redirect:/board/list";
		
	}
	
	// 페이지 삭제
	@PostMapping("/delete")
	public String boartdDeletePOST(int bno, RedirectAttributes rttr) {
		
		bservice.delete(bno);
		
		rttr.addFlashAttribute("result", "delete success");
		
		return "redirect:/board/list";
	}
}
