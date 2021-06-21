package com.example.dao;


import com.example.models.User;

public interface UserDAO {
	User selectuserbyemail(String email);
	boolean login(User user);
	void adduser(User user);
	void updateuserbyid(User user,int id);
}
