package com.reTubix.login.mapper;

import com.reTubix.login.domain.MemberVO;

public interface UserRegMapper {

	int userEmailCheck(String email);
	
	int userRegister(MemberVO user);
}
