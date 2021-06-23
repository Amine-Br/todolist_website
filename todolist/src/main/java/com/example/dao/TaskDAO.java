package com.example.dao;
import java.util.List;

import com.example.models.Task;


public interface TaskDAO {
List<Task> selectalltasks(int user_id);
Task selecttaskbyid(int id);
void addtask(Task task);
void deletetaskbyid(int id);
void updatetaskbyid(Task task,int id);
void updatetaskstatusbyid(String Status,int id);

}
