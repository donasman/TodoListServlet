package com.study.todo_project.vo;

import com.study.todo_project.dto.InsertTodoListResDto;
import com.study.todo_project.dto.SearchTodoListDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoListVo {
	private int TodoListID;
	private String TodoListDate;
	private String TodoListContent;
	//DB에 넣는 VO 객체로 변환
	public InsertTodoListResDto toInsertDto(int successCount) {
		return InsertTodoListResDto.builder()
				.successCount(successCount)
				.TodoListId(TodoListID)
				.TodoListDate(TodoListDate)
				.TodoListContent(TodoListContent)
				.build();
	}
	public SearchTodoListDto toSearchDto() {
		return SearchTodoListDto.builder()
				.TodoListId(TodoListID)
				.TodoListDate(TodoListDate)
				.TodoListContent(TodoListContent)
				.build();
	}
}

