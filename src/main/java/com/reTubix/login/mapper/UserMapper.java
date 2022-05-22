package com.reTubix.login.mapper;

import com.reTubix.exception.NotUserException;
import com.reTubix.login.domain.MemberVO;


public interface UserMapper {
	
	int createUser(MemberVO user);
	
	MemberVO findUserByEmail(String email);
	
	MemberVO isLoginOk(String email, String pwd) throws NotUserException;


}
