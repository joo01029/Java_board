package com.java.board.domain.repo;

import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
	Long countByBoard(Board board);
}
