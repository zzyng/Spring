package com.care.cookie_session.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	@RequestMapping("cookie/index")
	public void index(HttpServletResponse res) throws IOException {
		
		Cookie cookie = new Cookie("cookieName", "cookieValue");
		cookie.setMaxAge(30);
		res.addCookie(cookie);
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.print("<a href=\"result\" style=\"text-decoration: none\">클릭하면 확인 페이지로 이동합니다.</a>");
//		return "cookie/index";
	}
	
	@RequestMapping("cookie/result")
	public void result(@CookieValue(value = "cookieName", required = false) Cookie clientSendCookie, 
			Model model, HttpServletRequest req) {
		if(clientSendCookie == null) {
			model.addAttribute("clientSendCookie", "쿠키는 없어요");
		}else {
			model.addAttribute("clientSendCookie", clientSendCookie.getValue());
		}
		
	}
	
}
