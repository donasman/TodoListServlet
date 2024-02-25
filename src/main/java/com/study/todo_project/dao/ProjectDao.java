package com.study.todo_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.todo_project.config.DBConnectionMgr;
import com.study.todo_project.vo.TodoListVo;

public class ProjectDao {
	private DBConnectionMgr pool;
	private static ProjectDao instance;

	public static ProjectDao getInstance() {
		if(instance == null) {
			instance = new ProjectDao();
		}
		return instance;
	}
	
	private ProjectDao() {
		pool = DBConnectionMgr.getInstance();
	}
	// 조회
	
	public List<TodoListVo> getTodoList() {
		List<TodoListVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from TodoList_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoListVo todoListVo = TodoListVo.builder()
									.TodoListID(rs.getInt(1))
									.TodoListDate(rs.getString(2))
									.TodoListContent(rs.getString(3))
									.build();
				list.add(todoListVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	
	//중복 거르기
	public TodoListVo findTodoListByTodoListDate(String TodoListDate) {
		TodoListVo todoListVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from TodoList_tb where TodoList_date = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TodoListDate);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todoListVo = TodoListVo.builder()
						.TodoListID(rs.getInt(1))
						.TodoListDate(rs.getString(2))
						.TodoListContent(rs.getString(3))
						.build();
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return todoListVo;
	}
	
	
	
	// DB insert
	public int save(TodoListVo todoListVo) {
		int sucessCount = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "insert into TodoList_tb values(0, ?, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, todoListVo.getTodoListDate());
			pstmt.setString(2, todoListVo.getTodoListContent());
			
			sucessCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				todoListVo.setTodoListID(rs.getInt(1));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return sucessCount;
	}
}
