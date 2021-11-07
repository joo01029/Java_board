package com.java.board.domain.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MakeBoardDto {
	@NotBlank(message = "공백 불가능")
	private String title;
	@NotBlank(message = "공백 불가능")
	private String content;
}
