package com.reTubix.main.mapper;

import java.util.List;

import com.reTubix.login.domain.MemberVO;
import com.reTubix.main.domain.TrailerVO;

public interface MainMapper {
	
	public List<MemberVO>subscribeList(String email);
	
	public List<TrailerVO> mainTrailer();
	
	public List<TrailerVO> MovieList(String KeyWord);
	
	public List<TrailerVO> SF_Movie();
	
	public List<TrailerVO> CO_Movie();
	
	public List<TrailerVO> AC_Movie();
	
	public List<TrailerVO> HO_Movie();
	
	public List<TrailerVO> RO_Movie();
	
	public List<TrailerVO> Drama();
	
	public List<TrailerVO> clickAlign();
		
	public List<TrailerVO> goodAlign();
	
	public List<TrailerVO> recommendList();
	
	public List<TrailerVO> zzimList(String email);
	
	public List<TrailerVO> historyList(String email);
	
	public List<TrailerVO> onlyMovie();
	
	public List<TrailerVO> searchList(String keyword);

}
