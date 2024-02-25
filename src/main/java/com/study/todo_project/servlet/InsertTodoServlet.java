package com.study.todo_project.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.todo_project.Service.TodoListService;
import com.study.todo_project.dto.InsertTodoListReqDto;
import com.study.todo_project.utils.RequestUtil;
import com.study.todo_project.utils.ResponseEntity;


@WebServlet("/todo")
public class InsertTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListService todoListService;

    public InsertTodoServlet() {
        super();
        todoListService = TodoListService.getInstance();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InsertTodoListReqDto reqDto = RequestUtil.convertJsonData(request, InsertTodoListReqDto.class); 
		
		if(todoListService.isDuplicateTodoListDate(reqDto.getTodoListDate())) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMessage", "중복된 날짜입니다.");
			
			ResponseEntity.ofJson(response, 400, responseMap);
			return;
		}
		
		ResponseEntity.ofJson(response, 201, todoListService.addTodoList(reqDto));
		


	    
	    
	}

}
