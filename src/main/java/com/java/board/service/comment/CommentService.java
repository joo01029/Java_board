package com.java.board.service.comment;

import com.java.board.domain.response.comment.CommentRo;

public interface CommentService {
	CommentRo writeComment(Long user_idx, String comment, Long board_id);
}
