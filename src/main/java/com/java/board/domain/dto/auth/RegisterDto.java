package com.java.board.domain.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterDto {
	@NotBlank(message = "id 공백 불가능")
	@Length(min=4,max = 40, message = "길이 4~40")
	private String id;
	@NotBlank(message = "password 공백 불가능")
	@Length(min=8,max = 40, message = "길이 8~40")
	private String password;
	@NotBlank(message = "name 공백 불가능")
	@Length(min=4,max = 40, message = "길이 4~40")
	private String name;
}
