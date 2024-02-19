package com.study.todo_project.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoListEntity {
	private int TodoListID;
	private String TodoListDate;
	private String TodoListContent;
}
