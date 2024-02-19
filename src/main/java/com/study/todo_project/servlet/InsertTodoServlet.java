package com.study.todo_project.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonString;
import com.study.todo_project.dao.ProjectDao;
import com.study.todo_project.entity.TodoListEntity;


@WebServlet("/todo")
public class InsertTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectDao;

    public InsertTodoServlet() {
        super();
        projectDao = ProjectDao.getInstance();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
	    
	    // JSON 문자열을 읽어와서 TodoListEntity 객체로 변환
	    BufferedReader reader = request.getReader();
	    StringBuilder jsonStringBuilder = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        jsonStringBuilder.append(line);
	    }
	    String json = jsonStringBuilder.toString();

	    TodoListEntity todoListEntity = gson.fromJson(json, TodoListEntity.class);
	    
	    // 데이터베이스에 저장
	    int successCount = projectDao.save(todoListEntity);
	    
	    // 응답으로 TodoListEntity 객체를 JSON 형식으로 전송
	    response.getWriter().println(gson.toJson(todoListEntity));
	    response.getWriter().println(successCount);

	    
	    
	}

}
