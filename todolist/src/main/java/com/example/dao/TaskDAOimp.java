package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Task;
import com.example.models.User;

public class TaskDAOimp implements TaskDAO{
	
	private static final String SELECT_ALL_FROM_TASKS="select id, user_id, todo, task_date, status from tasks where user_id=?";
	private static final String SELECT_TASK_BY_ID="select id, user_id, todo, task_date, status from tasks where id =?";
	private static final String INSERT_TASK="INSERT INTO tasks(user_id, todo, task_date, status) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_TASK_BY_ID="Update tasks set user_id = ?,todo= ?,task_date= ?, status= ? where id= ?";
	private static final String UPDATE_TASKSTATUS_BY_ID="Update tasks set status= ? where id= ?";
	private static final String DELETE_TASK_BY_ID="DELETE FROM tasks WHERE id=?;";
	
	DBconnection db=new DBconnection();

	@Override
	public List<Task> selectalltasks(int user_id) {
		List<Task> Tasks=new ArrayList<Task>();
		try {
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_TASKS);
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id=rs.getInt("id");
				String todo = rs.getString("todo");
				Date task_date = rs.getDate("task_date");
				String status = rs.getString("status");

				Task task = new Task();
				task.setId(id);
				task.setUser_id(user_id);
				task.setTodo(todo);
				task.setTask_date(task_date);
				task.setStatus(status);
				
				Tasks.add(task);
			}
		} catch (SQLException e) {
        	e.printStackTrace();
		}
		return Tasks;
	}

	@Override
	public Task selecttaskbyid(int id) {
		Task task=null;
		try {
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int user_id=rs.getInt("user_id");
				String todo = rs.getString("todo");
				Date task_date = rs.getDate("task_date");
				String status = rs.getString("status");

				task = new Task();
				task.setId(id);
				task.setUser_id(user_id);
				task.setTodo(todo);
				task.setTask_date(task_date);
				task.setStatus(status);
			}
		} catch (SQLException e) {
        	e.printStackTrace();
		}
		return task;
	}

	@Override
	public void addtask(Task task) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK);
			preparedStatement.setInt(1, task.getUser_id());
			preparedStatement.setString(2, task.getTodo());
			preparedStatement.setDate(3, task.getTask_date());
			preparedStatement.setString(4, task.getStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}		
	}

	@Override
	public void deletetaskbyid(int id) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}				
	}

	@Override
	public void updatetaskbyid(Task task, int id) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_BY_ID);
			preparedStatement.setInt(1, task.getUser_id());
			preparedStatement.setString(2, task.getTodo());
			preparedStatement.setDate(3, task.getTask_date());
			preparedStatement.setString(4, task.getStatus());
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}						
	}

	@Override
	public void updatetaskstatusbyid(String Status, int id) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASKSTATUS_BY_ID);
			preparedStatement.setString(1, Status);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}				
	}

}
