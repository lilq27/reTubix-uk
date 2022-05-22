package com.reTubix.login.service;

import com.reTubix.exception.NotUserException;
import com.reTubix.login.domain.MemberVO;

public interface UserService {
	
	MemberVO findUserByEmail(String email);
	
	MemberVO isLoginOk(String email, String pwd) throws NotUserException;	
	
	
}
