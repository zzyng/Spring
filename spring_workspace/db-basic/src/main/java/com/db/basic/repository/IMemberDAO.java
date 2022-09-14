package com.db.basic.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.db.basic.dto.MemberDTO;
import com.db.basic.dto.PostDTO;

@Repository
public interface IMemberDAO {

	int member(MemberDTO member);

	MemberDTO login(String id);

	List<MemberDTO> list();

	void update(MemberDTO member);

	void delete(String id);

	int doubleCheck(String id);

	void post(PostDTO post);

}
