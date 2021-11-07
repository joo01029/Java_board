package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import com.java.board.domain.repo.BoardRepo;
import com.java.board.domain.repo.UserRepo;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.domain.response.user.UserRo;
import com.java.board.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepo boardRepo;
	private final UserRepo userRepo;
	private final UserService userService;
	@Transactional
	@Override
	public BoardRo makeBoard(MakeBoardDto makeBoardDto, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		log.info(String.valueOf(user));
		Board board = Board.builder()
				.title(makeBoardDto.getTitle())
				.content(makeBoardDto.getContent())
				.build();
		user.add(board);

		BoardRo boardRo = changeBoardToBoardRo(boardRepo.save(board));
		boardRo.setLikeStatus(false);
		boardRo.setCommentNum(0);
		boardRo.setLikeNum(0);
		return boardRo;
	}

	@Override
	public List<BoardRo> getBoards(Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		return boardRepo.findAll().stream()
				.map((board)->{
					BoardRo boardRo = changeBoardToBoardRo(board);

				});
	}

	private BoardRo changeBoardToBoardRo(Board board){
		UserRo user = UserRo.builder()
				.id(board.getUser().getIdx())
				.name(board.getUser().getName())
				.build();
		return BoardRo.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.user(user)
				.build();
	}

}
