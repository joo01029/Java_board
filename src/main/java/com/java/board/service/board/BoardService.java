package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;

import com.java.board.domain.entity.User;
import com.java.board.domain.response.board.BoardRo;

import java.util.List;

public interface BoardService {
	BoardRo makeBoard(MakeBoardDto makeBoardDto, Long user_idx);
	List<BoardRo> getBoards(Long user_idx);
}
