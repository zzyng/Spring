package com.care.cookie_session.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionQuizController {
	@Autowired HttpSession session;
	
	@GetMapping("session/login")
	public void login() {} // 로그인 폼을 클라이언트에게 제공
	
	@PostMapping("session/login")
	public String login(String id, String pw) {
		// 전달 받은 클라이언트의 id/pw를 위 변수와 비교 후 로그인 성공 / 실패 
		if("admin".equals(id) && "1234".equals(pw)) {
			session.setAttribute("id", "admin");
			session.setAttribute("pw", "1234");
			return "redirect:index";
		}
		// 성공일 경우 index 페이지로 이동
		return "session/login";
		// 실패일 경우 login 페이지로 이동
	}
	@RequestMapping("session/logout")
	public String logout() {// 세션 삭제 후 index페이지로 이동
		session.invalidate();
		return "redirect:index";
	} 
	@RequestMapping("session/index")
	public void index() {} //  로그인 | 로그아웃 페이지이동 a태그로 구성
}
