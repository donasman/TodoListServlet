package com.study.todo_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.study.todo_project.config.DBConnectionMgr;
import com.study.todo_project.entity.TodoListEntity;

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
	
	public int save(TodoListEntity todoListEntity) {
		int sucessCount = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			sql = "insert into TodoList_tb values(0, ?, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, todoListEntity.getTodoListDate());
			pstmt.setString(2, todoListEntity.getTodoListContent());
			
			sucessCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				todoListEntity.setTodoListID(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
				
		
		
		return sucessCount;
	}
}
