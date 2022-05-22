package com.reTubix.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reTubix.exception.NotUserException;
import com.reTubix.login.domain.MemberVO;
import com.reTubix.login.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired	
	private UserMapper userMapper;

	
	@Override
	public MemberVO findUserByEmail(String email) {
		return userMapper.findUserByEmail(email);
		
	}

	@Override
	public MemberVO isLoginOk(String email, String pwd) throws NotUserException {
		MemberVO dbUser = findUserByEmail(email);		
		if(dbUser==null) throw new NotUserException("등록되지 않은 회원입니다. 회원가입 후 이용해주세요.");	
		if(!dbUser.getPwd().equals(pwd)) throw  new NotUserException("아이디 또는 비밀번호가 일치하지 않습니다.");	
		return dbUser;	
	}

}
