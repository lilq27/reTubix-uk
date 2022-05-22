package com.reTubix.main.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reTubix.common.FindInfoUtil;
import com.reTubix.main.domain.NaverApiDto;
import com.reTubix.main.domain.Trailer_ViewVO;
import com.reTubix.main.service.MainService;

@Controller
public class mainController {
	@Autowired
	private MainService mainservice;
	
	FindInfoUtil findInfoUtil = new FindInfoUtil();
	
	// 메인 트레일러
	@GetMapping("/main")
	public String main(HttpSession ses, Model m) {
		String email = (String) ses.getAttribute("email");

		List<Trailer_ViewVO> TrailerList = this.mainservice.mainTrailer();
		
		
		
		List<Trailer_ViewVO> SFMList = this.mainservice.SF_Movie();
		List<Trailer_ViewVO> COMList = this.mainservice.CO_Movie();
		List<Trailer_ViewVO> ACMList = this.mainservice.AC_Movie();
		List<Trailer_ViewVO> HOMList = this.mainservice.HO_Movie();
		List<Trailer_ViewVO> ROMList = this.mainservice.RO_Movie();
		List<Trailer_ViewVO> DramaList = this.mainservice.Drama();
		List<Trailer_ViewVO> ClickList = this.mainservice.clickAlign();
		
		List<Trailer_ViewVO> goodAlignList = this.mainservice.goodAlign();
		List<Trailer_ViewVO> recommendList = this.mainservice.recommendList();
		
		List<Trailer_ViewVO> zzimList = this.mainservice.zzimList(email);
		List<Trailer_ViewVO> historyList = this.mainservice.historyList(email);
		
		

		Collections.shuffle(TrailerList);
		Collections.shuffle(SFMList);
		Collections.shuffle(COMList);
		Collections.shuffle(ACMList);
		Collections.shuffle(HOMList);
		Collections.shuffle(ROMList);
		Collections.shuffle(DramaList);
		Collections.shuffle(ClickList);
		Collections.shuffle(zzimList);
		Collections.shuffle(historyList);
		Collections.shuffle(goodAlignList);
		Collections.shuffle(recommendList);

		m.addAttribute("mainTrailer", TrailerList);
		m.addAttribute("MS_title", SFMList);
		m.addAttribute("MC_title", COMList);
		m.addAttribute("MA_title", ACMList);
		m.addAttribute("MH_title", HOMList);
		m.addAttribute("MR_title", ROMList);
		m.addAttribute("D_title", DramaList);
		m.addAttribute("clickSlider", ClickList);
		m.addAttribute("zzimListTitle", zzimList);
		m.addAttribute("historyListTitle", historyList);
		
		m.addAttribute("reListTitle", recommendList);
		m.addAttribute("goodTitle", goodAlignList);

		m.addAttribute("zzimListSize", zzimList.size());
		m.addAttribute("historyListSize", historyList.size());

		return "main/main";
	}

	
	@ResponseBody
	@GetMapping("/search/movieList")
	public List<Trailer_ViewVO> readMovie(@RequestParam("KeyWord") String keyWord) {

		List<Trailer_ViewVO> MovieList = this.mainservice.MovieList(keyWord);
		
		return MovieList;
	}
	
	@ResponseBody
	@GetMapping(value = "/naverAPI/movieList", produces = "application/json; charset=UTF-8")
	public List<NaverApiDto> naverAPI(@RequestParam("movieName") String movieName) {
		
		return mainservice.naverApi(movieName);
	}

	@ResponseBody
	@RequestMapping(value = "/tredingMovie", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String MovieTrend() throws IOException {
		
		String stringURL = "https://api.themoviedb.org/3/trending/movie/day?api_key=44a1e09be53f66de396d8c69acba58c6";
		
		return findInfoUtil.findInfo(stringURL);
	}
	
	@ResponseBody
	@RequestMapping(value = "/tredingTV", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String TVTrend() throws IOException {
		
		String stringURL = "https://api.themoviedb.org/3/trending/tv/day?api_key=44a1e09be53f66de396d8c69acba58c6";
				
		return findInfoUtil.findInfo(stringURL);
	}
	
	@ResponseBody
	@RequestMapping(value = "/latestMovie", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String latestMovie() throws IOException {
		
		String stringURL = "https://api.themoviedb.org/3/movie/latest?api_key=44a1e09be53f66de396d8c69acba58c6&language=ko";
				
		return findInfoUtil.findInfo(stringURL);
	}
	
	@ResponseBody
	@RequestMapping(value = "/latestTV", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String latestTV() throws IOException {
		
		String stringURL = "https://api.themoviedb.org/3/tv/latest?api_key=44a1e09be53f66de396d8c69acba58c6&language=ko";


		return findInfoUtil.findInfo(stringURL);
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchInfo", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String searchInfo(
			@RequestParam String whichGenre, @RequestParam String apiKey, @RequestParam String language, 
			@RequestParam String title, @RequestParam(defaultValue = "false") String releaseYear, @RequestParam(defaultValue = "false") String year
			) throws IOException {
		
		title = URLEncoder.encode(title, "UTF-8");
		title = title.replace ( "+" , "%20" );
		String stringURL = "https://api.themoviedb.org/3/search/"+ whichGenre +"?api_key="+ apiKey +"&language="+ language +"&page=1&include_adult=false&query=" + title + "&" + releaseYear + "=" + year; 
		
		System.out.println(stringURL);
		
		return findInfoUtil.findInfo(stringURL);
	}
}
