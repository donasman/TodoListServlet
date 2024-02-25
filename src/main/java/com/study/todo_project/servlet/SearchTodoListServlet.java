package com.study.todo_project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.todo_project.Service.TodoListService;
import com.study.todo_project.utils.ResponseEntity;


@WebServlet("/TodoList")
public class SearchTodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListService todoListService;
	

    public SearchTodoListServlet() {
        super();
        todoListService = TodoListService.getInstance();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResponseEntity.ofJson(response, 200, todoListService.searchTodoList());
		response.getWriter().println();
		
	}


}
