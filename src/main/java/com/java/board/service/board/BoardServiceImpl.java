package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import com.java.board.domain.repo.BoardRepo;
import com.java.board.domain.repo.CommentRepo;
import com.java.board.domain.repo.LikeRepo;
import com.java.board.domain.repo.UserRepo;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.domain.response.user.UserRo;
import com.java.board.exception.CustomException;
import com.java.board.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepo boardRepo;
	private final UserService userService;
	private final CommentRepo commentRepo;
	private final LikeRepo likeRepo;
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
		boardRo.setCommentNum(0L);
		boardRo.setLikeNum(0L);
		return boardRo;
	}

	@Override
	public List<BoardRo> getBoards(Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC,"id")).stream()
				.map((board)->{
					BoardRo boardRo = changeBoardToBoardRo(board);

					Long likeNum = likeRepo.countByBoard(board);
					Long commentNum = commentRepo.countByBoard(board);
					Boolean likeStatus = likeRepo.findByBoardAndUser(board, user).isPresent();

					boardRo.setCommentNum(commentNum);
					boardRo.setLikeNum(likeNum);
					boardRo.setLikeStatus(likeStatus);

					return boardRo;
				})
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void updateBoard(Long board_id, MakeBoardDto makeBoardDto, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		Board board = boardRepo.findByUserAndId(user, board_id)
				.orElseGet(()->{
					throw new CustomException(HttpStatus.NOT_FOUND,"게시글을 찾을 수 없습니다.");
				});
		board.setTitle(makeBoardDto.getTitle());
		board.setContent(makeBoardDto.getContent());

		boardRepo.save(board);
	}

	@Override
	@Transactional
	public void removeBoard(Long board_id, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		boardRepo.findByUserAndId(user, board_id)
				.orElseGet(()->{
					throw new CustomException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
				});

		boardRepo.deleteById(board_id);

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
