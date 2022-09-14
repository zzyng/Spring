package com.db.basic.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.basic.dto.MemberDTO;
import com.db.basic.dto.PostDTO;
import com.db.basic.service.KakaoService;
import com.db.basic.service.MemberService;

@Controller
public class MemberController {
	@Autowired private MemberService service;
	
	@ResponseBody
	@GetMapping(value="doubleCheck", produces = "text/html; charset=UTF-8")
	public String doubleCheck(String id) {
		System.out.println("아이디 : " + id);
		return service.doubleCheck(id);
	}
	
	@RequestMapping("index")
	public String index() {
		return "member/index";
	}
	@GetMapping("member")
	public String member() {
		return "member/member";
	}
	
	@PostMapping("member")
	public String member(MemberDTO member, String confirmPw, PostDTO post, Model model) {
		String msg = service.member(member, confirmPw, post);
		model.addAttribute("msg", msg);
		if(msg.equals("가입 완료"))
			return "member/index";
		return "member/member";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	@PostMapping("login")
	public String login(MemberDTO member, Model model) {
		String msg = service.login(member);
		model.addAttribute("msg", msg);
		if(msg.equals("로그인 성공"))
			return "member/index";
		return "member/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		String accessToken = (String)session.getAttribute("accessToken");
		if(accessToken != null) {
			kakaoService.logout2();
			session.removeAttribute("accessToken");
		}
		session.invalidate();
		return "member/index";
	}
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", service.list());
		return "member/list";
	}
	@GetMapping("update")
	public String update() {
		return "member/update";
	}
	@PostMapping("update")
	public String update(MemberDTO member, String confirmPw, Model model) {
		String msg = service.update(member, confirmPw);
		model.addAttribute("msg", msg);
		if(msg.equals("수정 완료")) {
			return "member/index";
		}
		return "member/update";
	}
	
	@GetMapping("delete")
	public String delete() {
		return "member/delete";
	}
	@PostMapping("delete")
	public String delete(String pw, String confirmPw, Model model) {
		String msg = service.delete(pw, confirmPw);
		model.addAttribute("msg", msg);
		if(msg.equals("삭제 완료"))
			return "member/index";
		else if(msg.equals("로그인 후 이용하세요."))
			return "member/login";
		
		return "member/delete";
	}
	/*
	 * http://localhost:8085/basic/kakaoLogin?
	 * code=aMoHeKvVt4L2EKrwtbcwdP7rjTr8eG295Z2CctAT5x1VY1QkVuWgUEa51XSGe4CkKbOvYwo9dGkAAAGCEORzxQ
	 */
	@Autowired private KakaoService kakaoService;
	@RequestMapping("kakaoLogin")
	public String kakaoLogin(String code, HttpSession session) {
//		System.out.println("code : " + code);
		String accessToken = kakaoService.getAccessToken(code);
		HashMap<String, String> map = kakaoService.getUserInfo(accessToken);
		System.out.println("닉네임 : " + map.get("nickname"));
		System.out.println("이메일 : " + map.get("email"));
		session.setAttribute("id", map.get("email"));
		session.setAttribute("name", map.get("nickname"));
		session.setAttribute("accessToken", accessToken);
		return "member/index";
	}
}

