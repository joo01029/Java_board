package com.java.board.service.user;

import com.java.board.domain.entity.User;
import com.java.board.domain.response.user.UserRo;

public interface UserService {
	User getUserById(String id);
	User getUserByIdx(Long id);
	UserRo userToRo(User user);
}
