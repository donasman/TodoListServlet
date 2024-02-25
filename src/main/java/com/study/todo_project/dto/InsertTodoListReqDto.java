package com.study.todo_project.dto;

import com.study.todo_project.vo.TodoListVo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertTodoListReqDto {
	
	private String TodoListDate;
	private String TodoListContent;
	
	public TodoListVo toVo() {
		return TodoListVo.builder()
				.TodoListDate(TodoListDate)
				.TodoListContent(TodoListContent)
				.build();
	}
 

}
