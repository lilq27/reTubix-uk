package com.reTubix.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reTubix.login.domain.MemberVO;
import com.reTubix.main.domain.NaverApiDto;
import com.reTubix.main.domain.Trailer_ViewVO;
import com.reTubix.main.mapper.MainMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("MainService")
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mainMapper;

	@Override
	public List<NaverApiDto> naverApi(String movieName) {
		String clientId = "rzxjIt0b7t1Fpv_Oi0fL";
		String clientSecret ="ZXkYZTVZCY";
		
		try {
			movieName = URLEncoder.encode(movieName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + movieName;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        String responseBody = getURL(apiURL, requestHeaders);
        NaverApiDto naverApiDto= null;
        List<NaverApiDto> apiList = new ArrayList<NaverApiDto>();
        try {
        	JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
			JSONArray jsonArray = (JSONArray)jsonObject.get("items");
			
			for(int i = 0; i < jsonArray.size(); i++) {
				naverApiDto = new NaverApiDto();
				
				JSONObject jsonMovie = (JSONObject) jsonArray.get(i);
				naverApiDto.setActor(jsonMovie.get("actor").toString());
				naverApiDto.setDirector(jsonMovie.get("director").toString());
				naverApiDto.setImage(jsonMovie.get("image").toString());
				naverApiDto.setLink(jsonMovie.get("link").toString());
				naverApiDto.setPubDate(jsonMovie.get("pubDate").toString());
				naverApiDto.setSubtitle(jsonMovie.get("subtitle").toString());
				naverApiDto.setTitle(jsonMovie.get("title").toString());
				naverApiDto.setUserRating(jsonMovie.get("userRating").toString());
				
				apiList.add(naverApiDto);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return apiList;
	}
	
	private static String getURL(String apiURL, Map<String, String> requestHeaders) {
		
		HttpURLConnection con = connect(apiURL);
		
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}
	

	private static HttpURLConnection connect(String apiURL) {
		try {
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiURL, e);
		}
	}
	
	private static String readBody(InputStream inputStream) {
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			
			return responseBody.toString();
			
		} catch (Exception e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	
	@Override
	public List<MemberVO> subscribeList(String email) {
		// TODO Auto-generated method stub
		return this.mainMapper.subscribeList(email);
	}

	@Override
	public List<Trailer_ViewVO> mainTrailer() {
		// TODO Auto-generated method stub
		return this.mainMapper.mainTrailer();
	}
	
	@Override
	public List<Trailer_ViewVO> MovieList(String KeyWord) {
		// TODO Auto-generated method stub
		return this.mainMapper.MovieList(KeyWord);
	}

	@Override
	public List<Trailer_ViewVO> SF_Movie() {
		// TODO Auto-generated method stub
		return this.mainMapper.SF_Movie();
	}

	@Override
	public List<Trailer_ViewVO> CO_Movie() {
		// TODO Auto-generated method stub
		return this.mainMapper.CO_Movie();
	}

	@Override
	public List<Trailer_ViewVO> AC_Movie() {
		// TODO Auto-generated method stub
		return this.mainMapper.AC_Movie();
	}

	@Override
	public List<Trailer_ViewVO> HO_Movie() {
		// TODO Auto-generated method stub
		return this.mainMapper.HO_Movie();
	}

	@Override
	public List<Trailer_ViewVO> RO_Movie() {
		// TODO Auto-generated method stub
		return this.mainMapper.RO_Movie();
	}

	@Override
	public List<Trailer_ViewVO> Drama() {
		// TODO Auto-generated method stub
		return this.mainMapper.Drama();
	}

	@Override
	public List<Trailer_ViewVO> clickAlign() {
		// TODO Auto-generated method stub
		return this.mainMapper.clickAlign();
	}

	@Override
	public List<Trailer_ViewVO> goodAlign() {
		// TODO Auto-generated method stub
		return this.mainMapper.goodAlign();
	}

	@Override
	public List<Trailer_ViewVO> recommendList() {
		// TODO Auto-generated method stub
		return this.mainMapper.recommendList();
	}

	@Override
	public List<Trailer_ViewVO> zzimList(String email) {
		// TODO Auto-generated method stub
		return this.mainMapper.zzimList(email);
	}

	@Override
	public List<Trailer_ViewVO> historyList(String email) {
		// TODO Auto-generated method stub
		return this.mainMapper.historyList(email);
	}

	@Override
	public List<Trailer_ViewVO> onlyMovie() {
		// TODO Auto-generated method stub
		return this.mainMapper.onlyMovie();
	}

	@Override
	public List<Trailer_ViewVO> searchList(String keyword) {
		// TODO Auto-generated method stub
		return this.mainMapper.searchList(keyword);
	}
}
