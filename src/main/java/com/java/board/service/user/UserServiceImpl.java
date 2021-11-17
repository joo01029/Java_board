package com.java.board.service.user;

import com.java.board.domain.entity.User;
import com.java.board.domain.repo.UserRepo;
import com.java.board.domain.response.user.UserRo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;

	@Override
	public User getUserById(String id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User getUserByIdx(Long idx) {
		return userRepo.findByIdx(idx).orElse(null);
	}

	@Override
	public UserRo userToRo(User user) {
		return UserRo.builder()
				.id(user.getIdx())
				.name(user.getName())
				.build();
	}


}
