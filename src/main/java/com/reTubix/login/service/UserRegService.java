package com.reTubix.login.service;

import com.reTubix.login.domain.MemberVO;

public interface UserRegService {

	int userEmailCheck(String email);
	
	int userRegister(MemberVO user);
}
