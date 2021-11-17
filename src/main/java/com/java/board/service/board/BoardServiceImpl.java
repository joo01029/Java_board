package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import com.java.board.domain.repo.BoardRepo;
import com.java.board.domain.response.board.BoardDetailRo;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepo boardRepo;
	private final UserService userService;

	@Transactional
	@Override
	public BoardRo makeBoard(MakeBoardDto makeBoardDto, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);

		Board board = Board.builder()
				.title(makeBoardDto.getTitle())
				.content(makeBoardDto.getContent())
				.build();
		user.add(board);

		BoardRo boardRo = changeBoardToBoardRo(boardRepo.save(board));
		return boardRo;
	}

	@Override
	public List<BoardRo> getBoards(Long user_idx) {
		User user = userService.getUserByIdx(user_idx);

		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
				.map((board) -> {
					BoardRo boardRo = changeBoardToBoardRo(board);
					return boardRo;
				})
				.collect(Collectors.toList());
	}

	@Override
	public BoardDetailRo getBoard(Long user_idx, Long board_idx) {
		User user = new User();
		if(user_idx != null){
			user = userService.getUserByIdx(user_idx);
		}
		Board board = findBoardById(board_idx);
		Boolean isMine = checkBoardUser(board, user);
		return changeBoardToBoardDetailRo(board, isMine);
	}

	@Override
	@Transactional
	public void updateBoard(Long board_id, MakeBoardDto makeBoardDto, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		Board board = findBoardById(board_id);
		if(!checkBoardUser(board, user)){
			throw new CustomException(HttpStatus.BAD_REQUEST, "유저의 게시글이 아닙니다");
		}
		board.setTitle(makeBoardDto.getTitle());
		board.setContent(makeBoardDto.getContent());

		boardRepo.save(board);
	}

	@Override
	@Transactional
	public void removeBoard(Long board_id, Long user_idx) {
		User user = userService.getUserByIdx(user_idx);
		Board board = findBoardById(board_id);
		if(!checkBoardUser(board, user)){
			throw new CustomException(HttpStatus.BAD_REQUEST, "유저의 게시글이 아닙니다");
		}

		boardRepo.deleteById(board_id);

	}

	@Override
	public Board findBoardById(Long board_id) {
		return boardRepo.findById(board_id)
				.orElseGet(() -> {
					throw new CustomException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
				});


	}

	@Override
	public Boolean checkBoardUser(Board board, User user) {
		return board.getUser().getIdx().equals(user.getIdx());

	}

	private BoardDetailRo changeBoardToBoardDetailRo(Board board, Boolean isMine){
		UserRo user = userService.userToRo(board.getUser());
		return BoardDetailRo.builder()
				.id(board.getId())
				.title(board.getTitle())
				.user(user)
				.isMine(isMine)
				.build();
	}

	private BoardRo changeBoardToBoardRo(Board board) {
		UserRo user = userService.userToRo(board.getUser());
		return BoardRo.builder()
				.id(board.getId())
				.title(board.getTitle())
				.user(user)
				.build();
	}

}
