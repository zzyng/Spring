package com.db.basic.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.db.basic.dto.MemberDTO;
import com.db.basic.dto.PostDTO;
import com.db.basic.repository.IMemberDAO;

@Service
public class MemberService {
	@Autowired private IMemberDAO dao;
	@Autowired private HttpSession session;
	
	public String doubleCheck(String id) {
		if(id == null || id.isEmpty())
			return "입력 후 다시 시도하세요";
		
		int count = dao.doubleCheck(id);
		if(count == 0)
			return "사용 가능 아이디";
		return "중복 아이디";
	}
	
	public String member(MemberDTO member, String confirmPw, PostDTO post) {
		String authState = (String)session.getAttribute("authState");
		System.out.println("authState : "+ authState);
		if(authState == null || authState.equals("success") == false) {
			return "이메일 인증 후 다시 시도하세요";
		}
		
		if(member.getId() == null || member.getId().isEmpty())
			return "필수 정보 입니다.";
		if(member.getPw() == null || member.getPw().isEmpty())
			return "필수 정보 입니다.";
		if(member.getPw().equals(confirmPw) == false) {
			return "입력하신 두 비밀번호를 확인하세요";
		}
		MemberDTO check = dao.login(member.getId());
		if(check != null)
			return "다른 아이디로 가입을 시도하세요";
		
		/* 
		 *  비밀번호 암호화 
		 *  단방향, 양방향
		 *  단방향 : 암호화는 가능, 복호화(암호문 -> 평문) 불가능
		 *  양방향 : 암호화, 복호화
		 */
		//SQL> ALTER TABLE db_basic MODIFY pw varchar2(70);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptPw = encoder.encode(member.getPw());
//		System.out.println(encryptPw);
		member.setPw(encryptPw);
		
		int result = dao.member(member);
		if(result != 1)
			return "가입 실패";
		
		dao.post(post);
		
		session.removeAttribute("authState");
		return "가입 완료";
	}
	
	public String login(MemberDTO member) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "필수 정보입니다.";
		}
		MemberDTO check = dao.login(member.getId());
		if(check == null)
			return "아이디 또는 비밀번호를 확인 후 다시 시도하세요.";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		 평문(사용자입력), 암호문(DB 비번)
//		encoder.matches(member.getPw(), check.getPw()); 
		if(encoder.matches(member.getPw(), check.getPw()) == false) {
			return "아이디 또는 비밀번호를 확인 후 다시 시도하세요.";
		}
		
		session.setAttribute("id", check.getId());
		session.setAttribute("name", check.getName());
		session.setAttribute("email", check.getEmail());
		return "로그인 성공";
	}

	public List<MemberDTO> list() {
		List<MemberDTO> list = dao.list();
		return list;
	}

	public String update(MemberDTO member, String confirmPw) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "로그인 후 이용하세요.";
		}
		if(member.getPw() == null || member.getPw().isEmpty())
			return "필수 정보 입니다.";
		if(member.getPw().equals(confirmPw) == false) {
			return "입력하신 두 비밀번호를 확인하세요";
		}
		member.setId(sessionId);
		dao.update(member);
		session.invalidate();
		return "수정 완료";
	}

	public String delete(String pw, String confirmPw) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "로그인 후 이용하세요.";
		}
		if(pw == null || pw.isEmpty())
			return "필수 정보 입니다.";
		if(pw.equals(confirmPw) == false) {
			return "입력하신 두 비밀번호를 확인하세요";
		}
		MemberDTO result = dao.login(sessionId);
		if(result.getPw().equals(pw) == false)
			return "비밀번호를 확인하세요.";
		
		dao.delete(sessionId);
		session.invalidate();
		return "삭제 완료";
	}


}
