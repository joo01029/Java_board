package com.java.board.domain.repo;

import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.Like;
import com.java.board.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {
	Long countByBoard(Board board);
	Optional<Like> findByBoardAndUser(Board board, User user);
}
