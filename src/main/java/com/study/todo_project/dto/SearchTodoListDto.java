package com.study.todo_project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchTodoListDto {
	private int TodoListId;
	private String TodoListDate;
	private String TodoListContent;
}
