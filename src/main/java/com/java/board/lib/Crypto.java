package com.java.board.lib;

import com.java.board.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
@Component
public class Crypto {
	public String encryption(String text){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(text.getBytes(), 0, text.getBytes().length);
			return new BigInteger(1, messageDigest.digest()).toString(24);
		}catch (Exception e){
			e.printStackTrace();
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "암호화 실패");
		}
	}
}
