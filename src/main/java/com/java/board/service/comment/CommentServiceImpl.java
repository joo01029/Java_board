package com.java.board.service.comment;

import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.Comment;
import com.java.board.domain.entity.User;
import com.java.board.domain.repo.BoardRepo;
import com.java.board.domain.repo.CommentRepo;
import com.java.board.domain.repo.LikeRepo;
import com.java.board.domain.response.comment.CommentRo;
import com.java.board.domain.response.user.UserRo;
import com.java.board.service.board.BoardService;
import com.java.board.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	private final BoardService boardService;
	private final UserService userService;
	private final CommentRepo commentRepo;

	@Override
	public CommentRo writeComment(Long user_idx, String comment, Long board_id) {
		User user = userService.getUserByIdx(user_idx);
		Board board = boardService.findBoardById(board_id);
		Comment comment1 = Comment.builder()
				.comment(comment)
				.build();
		board.add(comment1);
		user.add(comment1);

		return commentToRo(commentRepo.save(comment1));
	}

	private CommentRo commentToRo(Comment comment){
		UserRo user = userService.userToRo(comment.getUser());
		return CommentRo.builder()
				.id(comment.getId())
				.comment(comment.getComment())
				.user(user)
				.build();
	}
}
