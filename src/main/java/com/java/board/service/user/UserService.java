package com.java.board.service.user;

import com.java.board.domain.entity.User;

public interface UserService {
	User getUserById(String id);
	User getUserByIdx(Long id);
}
