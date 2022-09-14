package com.db.basic.ajax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class AjaxController {
	// http://localhost:8085/basic/ex1
	@GetMapping("ex1")
	public String ex1() {
		System.out.println("ex1 GET 메소드 요청");
		return "ajax/ex1";
	}

	@ResponseBody
	// produces = "text/html; charset=UTF-8" : 클라이언트에서 한글데이터 응답 시 입력
	@PostMapping(value = "ex1", produces = "text/html; charset=UTF-8")
	public String ex1Post() {
		System.out.println("AJAX POST 메소드 요청");
		return "AJAX 방식의 서버 응답 데이터";
	}

	// http://localhost:8085/basic/ex2
	@GetMapping("ex2")
	public String ex2() {
		System.out.println("ex2 GET 메소드 요청");
		return "ajax/ex2";
	}

	@ResponseBody
	@PostMapping(value = "ex2", produces = "text/html; charset=UTF-8")
	public String ex2Post(@RequestBody(required = false) String requestData) {
		System.out.println(requestData);
		return "클라이언트가 송신한 데이터 : " + requestData;
	}

	@GetMapping("ex3")
	public String ex3() {
		System.out.println("ex3 GET 메소드 요청");
		return "ajax/ex3";
	}

	@ResponseBody
	@PostMapping(value = "ex3", produces = "application/json; charset=UTF-8")
	public HashMap<String, String> ex3Post() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin");
		map.put("pw", "1234");
		return map;
	}

	// http://localhost:8085/basic/ex4
	@GetMapping("ex4")
	public String ex4() {
		System.out.println("ex4 GET 메소드 요청");
		return "ajax/ex4";
	}

	@ResponseBody
	@PostMapping(value = "ex4", produces = "text/html; charset=UTF-8")
	public String ex4Post(@RequestBody(required = false) HashMap<String, String> map) {
		System.out.println("아이디 : " + map.get("id"));
		System.out.println("비밀번호 : " + map.get("pw"));
		System.out.println("이름 : " + map.get("name"));
		if ("admin".equals(map.get("id")) && "1234".equals(map.get("pw")))
			return "로그인 성공";
		return "로그인 실패";
	}

	// http://localhost:8085/basic/ex5
	@GetMapping("ex5")
	public String ex5() {
		return "ajax/ex5";
	}
	@ResponseBody
	@PostMapping(value = "ex5")
	public String ex5Post() throws FileNotFoundException, IOException {
		ClassPathResource resource = new ClassPathResource("ex5.json");
		FileReader reader = new FileReader(resource.getFile());
		BufferedReader buffer = new BufferedReader(reader);
		
		String data = "";
		while(true) {
			String tmp = buffer.readLine(); // ex5.json에서 한 줄씩 데이터를 읽어와서 반환, 파일 끝이라면 null을 반환
			if(tmp == null) //  파일의 끝이면 반복문 종료
				break;
			
			data += tmp;
		}
		buffer.close();
		return data;
	}
	// http://localhost:8085/basic/ex6
	@GetMapping("ex6")
	public String ex6() {
		return "ajax/ex6";
	}
	@ResponseBody
	@PostMapping(value = "ex6")
	public String ex6Post(@RequestBody(required = false) String title) throws FileNotFoundException, IOException {
		System.out.println("검색 타이틀 : " + title);
		
		ClassPathResource resource = new ClassPathResource("ex5.json");
		FileReader reader = new FileReader(resource.getFile());
		
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(reader, JsonObject.class);
//		System.out.println(obj.toString());
		
		if(title == null || title.isEmpty())
			return obj.toString();
		
		JsonArray arr = obj.getAsJsonArray("cd");
//		System.out.println(arr.toString());
//		System.out.println(arr.get(0));
//		System.out.println(arr.get(1));
		
	/*
{
"cd" : [
{ "title" : "Empire Burlesque", "artist" : "Bob Dylan", "price" : "10.90" },
{ "title" : "Empire Burlesque", "artist" : "Bob Dylan", "price" : "10.90" },
{ "title" : "Maggie May", "artist" : "Rod Stewart", "price" : "8.50" }
]
}
	 */
		String data = "{\"cd\" : [";
		for(int i = 0; i < arr.size(); i++) {
			//{ "title" : "Empire Burlesque", "artist" : "Bob Dylan", "price" : "10.90" }
			JsonObject tmp = (JsonObject) arr.get(i); // 여러 JSON 데이터 중  한 개 씩 반환
			String jsonTitle = tmp.get("title").getAsString(); // 타이틀 키의 값을 문자열로 반환
			boolean check = jsonTitle.equals(title);
			if(check) {
				data += "{ \"title\" : \"" + tmp.get("title").getAsString() + "\",";
				data +=	 " \"artist\" : \"" + tmp.get("artist").getAsString() + "\",";
				data += " \"price\" : \"" + tmp.get("price").getAsString()+"\" },";
			}
		}
		
		data = data.substring(0, data.length()-1);
		data += "]}";
		return data;
	}
	
	@Autowired private AjaxService service;
	
	@ResponseBody
	@GetMapping(value = "ex7Insert", produces = "text/html; charset=UTF-8")
	public String ex7Insert() throws FileNotFoundException, IOException {
		int result = service.insert();
		if(result == 0)
			return "DB 입력 실패";
		return "파일 -> DB로 이전 완료";
	}
	
	@GetMapping("ex7")
	public String ex7() {
		return "ajax/ex7";
	}
	
	@ResponseBody
	@PostMapping("ex7")
	public String ex7(@RequestBody(required = false)String title) {
		if(title == null || title.isEmpty()) {
			// 모든 데이터
			return service.titleAll();
		}else {
			// 검색어에 맞는 결과
			return service.titleSearch(title);
		}
	}
	@GetMapping("ex8")
	public String ex8() {
		return "ajax/ex8";
	}
	
	@ResponseBody
	@PostMapping("ex8")
	public String ex8(@RequestBody(required = false) HashMap<String, String> map) {
		return service.choose(map);
	}
}















