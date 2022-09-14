package com.care.cookie_session.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieQuizController {

	@RequestMapping("cookie/cookie")
	public void cookie(@CookieValue(value ="quizName", required = false) Cookie clientSendCookie, 
			Model model) {
		if(clientSendCookie != null)
			model.addAttribute("clientSendCookie" ,clientSendCookie.getValue() );
	}
	
	@RequestMapping("cookie/popup")
	public void popup() {}
	
	@RequestMapping("cookie/make")
	public String make(HttpServletResponse response) {
		Cookie cookie = new Cookie("quizName", "quizValue");
		cookie.setMaxAge(30);
		response.addCookie(cookie);
		return "redirect:popup";
	}
}
