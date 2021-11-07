package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import com.java.board.domain.response.board.BoardRo;

public interface BoardService {
	BoardRo makeBoard(MakeBoardDto makeBoardDto, Long user_idx);
}
