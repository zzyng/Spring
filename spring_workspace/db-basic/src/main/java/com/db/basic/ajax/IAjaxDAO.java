package com.db.basic.ajax;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface IAjaxDAO {

	int insert(AjaxDTO dto);

	ArrayList<AjaxDTO> titleAll();

	ArrayList<AjaxDTO> titleSearch(String title);

	ArrayList<AjaxDTO> choose(HashMap<String, String> map);

}
