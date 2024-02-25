package com.study.todo_project.Service;


import java.util.ArrayList;
import java.util.List;

import com.study.todo_project.dao.ProjectDao;
import com.study.todo_project.dto.InsertTodoListReqDto;
import com.study.todo_project.dto.InsertTodoListResDto;
import com.study.todo_project.dto.SearchTodoListDto;
import com.study.todo_project.vo.TodoListVo;

public class TodoListService {
	private static TodoListService instance;
	private ProjectDao projectDao;
	
	private TodoListService() {
		projectDao = ProjectDao.getInstance();
	}
	public static TodoListService getInstance() {
		if(instance == null) {
			instance = new TodoListService();
		}
		return instance;
	}
	
	public InsertTodoListResDto addTodoList(InsertTodoListReqDto insertTodoListReqDto) {
		TodoListVo todoListVo = insertTodoListReqDto.toVo();
		
		int successCount = projectDao.save(todoListVo);
		
		return todoListVo.toInsertDto(successCount);
	}
	
	public boolean isDuplicateTodoListDate(String TodoListDate) {
		return projectDao.findTodoListByTodoListDate(TodoListDate) != null;
	}
	
	public List<SearchTodoListDto> searchTodoList() {
		List<SearchTodoListDto> searchTodoListDtos = new ArrayList<>();
		List<TodoListVo> todoListVos = projectDao.getTodoList();
		
		for(TodoListVo todoListVo : todoListVos) {
			searchTodoListDtos.add(todoListVo.toSearchDto());
		}
		return searchTodoListDtos;
		
	}

}
