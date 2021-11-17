package com.java.board.controller;

import com.java.board.annotation.AutoLogging;
import com.java.board.domain.dto.comment.WriteCommentDto;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.comment.CommentRo;
import com.java.board.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@AutoLogging
	@PostMapping("/{board_id}")
	public ResponseData<CommentRo> writeComment(HttpServletRequest request, @Valid @RequestBody WriteCommentDto writeCommentDto, @PathVariable Long board_id){
		Long user_idx = Long.valueOf(request.getAttribute("user").toString());
		CommentRo comment = commentService.writeComment(user_idx, writeCommentDto.getComment(), board_id);
		return new ResponseData<>(comment);
	}
}
