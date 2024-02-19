package com.study.todo_project.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoList {
	private int TodoListID;
	private String TodoListDate;
	private String TodoListContent;
}
