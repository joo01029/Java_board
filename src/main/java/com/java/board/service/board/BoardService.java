package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;

import com.java.board.domain.entity.Board;
import com.java.board.domain.response.board.BoardDetailRo;
import com.java.board.domain.response.board.BoardRo;

import java.util.List;

public interface BoardService {
	BoardRo makeBoard(MakeBoardDto makeBoardDto);
	List<BoardRo> getBoards();
	BoardDetailRo getBoard(Long user_idx);
	void updateBoard(Long board_id, MakeBoardDto makeBoardDto);
	void removeBoard(Long board_id);

	Board findBoardById(Long board_id);
}
