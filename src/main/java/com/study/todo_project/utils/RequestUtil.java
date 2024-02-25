package com.study.todo_project.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUtil {
	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classOfT) throws IOException {
		// JSON 문자열 변환
		
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		
		String readLineData = null;
		
		while((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		
		requestJsonData = builder.toString();
		
		Gson gson = new Gson();
		
		return gson.fromJson(requestJsonData, classOfT);
	}

}
