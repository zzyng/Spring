package com.db.basic.ajax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class AjaxService {
	@Autowired private IAjaxDAO dao;

	public int insert() throws FileNotFoundException, IOException {
		ClassPathResource resource = new ClassPathResource("ex5.json");
		FileReader reader = new FileReader(resource.getFile());
		
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(reader, JsonObject.class);
		JsonArray arr = obj.getAsJsonArray("cd");
		ArrayList<Integer> results = new ArrayList<>();
		for(int i = 0; i < arr.size(); i++) {
			JsonObject tmp = (JsonObject) arr.get(i);
			
			AjaxDTO dto = new AjaxDTO();
			dto.setTitle(tmp.get("title").getAsString());
			dto.setArtist(tmp.get("artist").getAsString());
			dto.setPrice(tmp.get("price").getAsString());
			results.add(dao.insert(dto));
		}
		for(Integer check : results) {
			if(check != 1)
				return 0;
		}
		return 1;
	}
	/*
	 * AjaxDTO를 생성해서
	 * 파일의 데이터를 갖고 와
	 * AjaxDTO에 입력 후 
	 * IAjaxDAO를 호출하여 Mapper를 이용해 디비에 데이터가 담기도록 만들어주세요.
	 * 
	 * CREATE TABLE ajax(
	 *  title varchar2(30),
	 *  artist varchar2(30),
	 *  price varchar2(10)
	 * );
	 * 
	 */
	public String fromJson(ArrayList<AjaxDTO> list) {
		String data = "{\"cd\" : [";
		for(AjaxDTO tmp : list) {
			data += "{ \"title\" : \"" + tmp.getTitle() + "\",";
			data +=	 " \"artist\" : \"" + tmp.getArtist() + "\",";
			data += " \"price\" : \"" + tmp.getPrice() +"\" },";
		}
		data = data.substring(0, data.length()-1);
		data += "]}";
		return data;
	}
	public String titleAll() {
		ArrayList<AjaxDTO> list = dao.titleAll();
		String result = fromJson(list);
		return result;
	}

	public String titleSearch(String title) {
		ArrayList<AjaxDTO> list = dao.titleSearch(title);
		return fromJson(list);
	}
	public String choose(HashMap<String, String> map) {
		ArrayList<AjaxDTO> list = dao.choose(map);
		if(list.isEmpty() == false)
			return fromJson(list);
		return "";
	}
}


