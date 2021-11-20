package com.java.board.service.board;

import com.java.board.domain.dto.board.MakeBoardDto;
import com.java.board.domain.entity.Board;
import com.java.board.domain.repo.BoardRepo;
import com.java.board.domain.response.board.BoardDetailRo;
import com.java.board.domain.response.board.BoardRo;
import com.java.board.exception.CustomException;

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

	@Transactional
	@Override
	public BoardRo makeBoard(MakeBoardDto makeBoardDto) {

		Board board = Board.builder()
				.title(makeBoardDto.getTitle())
				.content(makeBoardDto.getContent())
				.writer(makeBoardDto.getWriter())
				.build();

		BoardRo boardRo = changeBoardToBoardRo(boardRepo.save(board));
		return boardRo;
	}

	@Override
	public List<BoardRo> getBoards() {

		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "updateAt")).stream()
				.map((board) -> {
					BoardRo boardRo = changeBoardToBoardRo(board);
					return boardRo;
				})
				.collect(Collectors.toList());
	}

	@Override
	public BoardDetailRo getBoard(Long board_idx) {
		Board board = findBoardById(board_idx);
		return changeBoardToBoardDetailRo(board);
	}

	@Override
	@Transactional
	public void updateBoard(Long board_id, MakeBoardDto makeBoardDto) {
		Board board = findBoardById(board_id);

		board.setTitle(makeBoardDto.getTitle());
		board.setContent(makeBoardDto.getContent());
		board.setWriter(makeBoardDto.getWriter());
		boardRepo.save(board);
	}

	@Override
	@Transactional
	public void removeBoard(Long board_id) {
		Board board = findBoardById(board_id);
		boardRepo.deleteById(board_id);
	}

	@Override
	public Board findBoardById(Long board_id) {
		return boardRepo.findById(board_id)
				.orElseGet(() -> {
					throw new CustomException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
				});


	}

	private BoardDetailRo changeBoardToBoardDetailRo(Board board){
		return BoardDetailRo.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(board.getWriter())
				.lastTime(board.getUpdateAt())
				.content(board.getContent())
				.build();
	}

	private BoardRo changeBoardToBoardRo(Board board) {
		return BoardRo.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(board.getWriter())
				.lastTime(board.getUpdateAt())
				.build();
	}

}
