package com.java.board.controller;

import com.java.board.annotation.AutoLogging;
import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.response.Response;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.board.BoardDetailRo;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@AutoLogging
	@PostMapping
	public ResponseData<BoardRo> makeBoard(@Valid @RequestBody MakeBoardDto makeBoardDto) {
		BoardRo board = boardService.makeBoard(makeBoardDto);
		return new ResponseData<>(board);
	}

	@AutoLogging
	@GetMapping("/list")
	public ResponseData<List<BoardRo>> getBoard() {
		List<BoardRo> boards = boardService.getBoards();
		return new ResponseData<>(boards);
	}

	@AutoLogging
	@GetMapping("/detail/{board_id}")
	public ResponseData<BoardDetailRo> getBoard(@PathVariable Long board_id) {
		BoardDetailRo board = boardService.getBoard(board_id);
		return new ResponseData<>(board);
	}

	@AutoLogging
	@DeleteMapping("/{board_id}")
	public Response removeBoard(@PathVariable Long board_id) {
		boardService.removeBoard(board_id);
		return new Response();
	}

	@AutoLogging
	@PutMapping("/{board_id}")
	public Response updateBoard(
			@PathVariable Long board_id,
			@Valid @RequestBody MakeBoardDto makeBoardDto) {
		boardService.updateBoard(board_id, makeBoardDto);
		return new Response();
	}
}
