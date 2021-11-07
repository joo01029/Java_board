package com.java.board.domain.repo;

import com.java.board.domain.entity.Board;
import com.java.board.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {
	Optional<Board> findByUserAndId(User user, Long id);
}
