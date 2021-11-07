package com.java.board.controller;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping
	public ResponseData<BoardRo> makeBoard(@Valid @RequestBody MakeBoardDto makeBoardDto, HttpServletRequest request){
		Long user_idx = Long.valueOf(request.getAttribute("idx").toString());
		BoardRo board = boardService.makeBoard(makeBoardDto, user_idx);
		return new ResponseData<>(board);
	}
}
