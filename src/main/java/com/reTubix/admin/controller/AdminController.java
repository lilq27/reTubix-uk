package com.reTubix.admin.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reTubix.common.FindInfoUtil;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class AdminController {

	@GetMapping("/admin")
	public String admin(Model m) {
		
		return "admin/admin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/adminSearchInfo", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String searchInfo(
			@RequestParam String whichGenre, @RequestParam String apiKey, @RequestParam String language, 
			@RequestParam String title, @RequestParam(defaultValue = "false") String releaseYear, @RequestParam(defaultValue = "false") String year
			) throws IOException {
		
		title = URLEncoder.encode(title, "UTF-8");
		title = title.replace ( "+" , "%20" );
		String stringURL = "https://api.themoviedb.org/3/search/"+ whichGenre +"?api_key="+ apiKey +"&language="+ language +"&page=1&include_adult=false&query=" + title + "&" + releaseYear + "=" + year; 
		
		System.out.println(stringURL);
		
		FindInfoUtil findInfoUtil = new FindInfoUtil();
		
		return findInfoUtil.findInfo(stringURL);
	}
}
