package com.java.board.controller;

import com.java.board.annotation.AutoLogging;
import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.response.Response;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@AutoLogging
	@PostMapping
	public ResponseData<BoardRo> makeBoard(@Valid @RequestBody MakeBoardDto makeBoardDto, HttpServletRequest request) {
		Long user_idx = Long.valueOf(request.getAttribute("idx").toString());
		BoardRo board = boardService.makeBoard(makeBoardDto, user_idx);
		return new ResponseData<>(board);
	}

	@AutoLogging
	@GetMapping
	public ResponseData<List<BoardRo>> getBoard(HttpServletRequest request) {
		Long user_idx = Long.valueOf(request.getAttribute("idx").toString());
		List<BoardRo> boards = boardService.getBoards(user_idx);
		return new ResponseData<>(boards);
	}

	@AutoLogging
	@DeleteMapping("/{board_id}")
	public Response removeBoard(@PathVariable Long board_id, HttpServletRequest request) {
		Long user_idx = Long.valueOf(request.getAttribute("idx").toString());
		boardService.removeBoard(board_id, user_idx);
		return new Response();
	}

	@AutoLogging
	@PutMapping("/{board_id}")
	public Response updateBoard(
			@PathVariable Long board_id,
			@Valid @RequestBody MakeBoardDto makeBoardDto,
			HttpServletRequest request) {
		Long user_idx = Long.valueOf(request.getAttribute("idx").toString());
		boardService.updateBoard(board_id, makeBoardDto, user_idx);
		return new Response();
	}
}
