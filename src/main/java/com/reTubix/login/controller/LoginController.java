package com.reTubix.login.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.reTubix.common.CommonUtil;
import com.reTubix.exception.NotUserException;
import com.reTubix.login.domain.MemberVO;
import com.reTubix.login.service.UserService;
import com.reTubix.main.service.MainService;
import com.zaxxer.hikari.metrics.micrometer.MicrometerMetricsTrackerFactory;

import io.opentelemetry.semconv.trace.attributes.SemanticAttributes.NetHostConnectionSubtypeValues;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class LoginController {
	
	@Autowired	
	private CommonUtil util;
	
	@Autowired
	private UserService userService;
	
	@Autowired
//	@Qualifier("mainservice")//중복될 때 지정해줌 
	private MainService mainservice;

	/**로그인 화면 진입: "localhost:9090/retubix/"*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		return "login/login";
	}
	
	/**로그인 처리*/ 
	
	@RequestMapping("/login")
	public String loginCheck(
			@RequestParam(name="email", defaultValue="") String email,
			@RequestParam(defaultValue="") String pwd,
			@RequestParam(defaultValue="false") boolean saveId,
			Model model,
			HttpSession ses,
			HttpServletResponse res
			) throws NotUserException {
		
		//유효성 체크
		if(email.trim().isEmpty()||pwd.isEmpty()) {
			return "redirect:/";	//로그인 페이지로 이동
		}
		
		/*로직처리: 로그인 인증처리 메소드 호출*/
		MemberVO loginUser = userService.isLoginOk(email, pwd);
		if(loginUser!=null) {
			ses.setAttribute("email", email);
			ses.setAttribute("loginUser", loginUser);
			ses.setAttribute("userMode", loginUser.getState());
			
			/**유저가 아이디 저장에 체크했을 경우: html에서 전달받은 쿠키 키값 "uid"사용*/
			Cookie ck=new Cookie("uid", loginUser.getEmail());
			if(saveId) ck.setMaxAge(7*24*60*60);//아이디 저장 7일간 유효
			/**유저가 아이디 저장에 체크하지 않았을 경우*/
			else ck.setMaxAge(0);	//아이디 저장 안했을 경우 유효기간 삭제하여 저장 안되게 처리
			ck.setPath("/");		//어디서든 쿠키에 접근할 수 있도록 "/"로 경로 처리
			res.addCookie(ck);		//response에 쿠키 추가
		}
		
		
		// 구독자 리스트
		List<MemberVO> email_subs = mainservice.subscribeList(email);
		if (email_subs != null) {

			ses.setAttribute("email_subs", email_subs);
		}
		
		WebDriver driver = new ChromeDriver();
		
		return "redirect:main";

		/*UserServiceImpl 나머지 구현:findUserByUserid()*/
	}
	
	/**로그아웃 처리*/
	@RequestMapping("/logout")
	public String logout(HttpSession ses) {
		ses.invalidate();	//세션 무효화
		return "redirect:/";	//로그인 페이지로 이동
	}
	
	/**접근 권한 처리*/
	@GetMapping("/user/mypage")
	public String showMyPage(Model m, HttpSession ses) {
		return "member/mypage";	
	}
	
}
