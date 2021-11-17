package com.java.board.domain.dto.comment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WriteCommentDto {
	@NotBlank(message = "공백 불가능")
	private String comment;
}
