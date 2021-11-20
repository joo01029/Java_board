package com.java.board.domain.repo;

import com.java.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {
	Optional<Board> findById(Long id);
}
