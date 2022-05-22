package com.reTubix.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.reTubix.login.domain.MemberVO;
import com.reTubix.login.mapper.UserRegMapper;

@Service
public class UserRegServiceImpl implements UserRegService {

	@Inject
	private UserRegMapper userregmapper;

	@Override
	public int userEmailCheck(String email) {

		return this.userregmapper.userEmailCheck(email);
	}

	@Override
	public int userRegister(MemberVO user) {

		return this.userregmapper.userRegister(user);
	}

}
