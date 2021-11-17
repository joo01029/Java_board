package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;

import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import com.java.board.domain.response.board.BoardRo;

import java.util.List;
import java.util.stream.Stream;

public interface BoardService {
	BoardRo makeBoard(MakeBoardDto makeBoardDto, Long user_idx);
	List<BoardRo> getBoards(Long user_idx);
	void updateBoard(Long board_id, MakeBoardDto makeBoardDto, Long user_idx);
	void removeBoard(Long board_id, Long user_idx);

	Board findBoardById(Long board_id);
	Board checkBoardUser(Long board_id, User user);
}
