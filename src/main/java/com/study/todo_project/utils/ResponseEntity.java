package com.study.todo_project.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseEntity {
	
	public static <T> void ofJson(HttpServletResponse response, int status, T body) throws IOException {
		//응답 객체 Json으로 만들기
		Gson gson = new Gson();
		response.setStatus(status);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(body));
	}
	

}
