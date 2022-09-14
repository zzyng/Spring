package com.care.cookie_session.session;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {
	//http://localhost:8085/cookie_session/session/make
	@RequestMapping("session/make")
	public void make(HttpSession session) {
		session.setAttribute("id", "admin");
		session.setAttribute("pw", "1234");
	}
	
	@RequestMapping("session/result")
	public void result() {}
	
	@RequestMapping("session/delete")
	public void delete(HttpSession session) {
		session.invalidate();
	}
	
}
