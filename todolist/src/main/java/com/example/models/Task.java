package com.example.models;

import java.sql.Date;

public class Task {
private int id,user_id;
private String todo,status;
private Date task_date;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getTodo() {
	return todo;
}
public void setTodo(String todo) {
	this.todo = todo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getTask_date() {
	return task_date;
}
public void setTask_date(Date task_date) {
	this.task_date = task_date;
}

}
