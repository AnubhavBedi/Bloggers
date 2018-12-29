package com.Backend.Dao;

import java.util.List;

import com.Backend.User;

public interface UserDao {
	
	public boolean registrerUser(User userObj);
	public boolean checkLogin(User userObj);
	public boolean updateOnlineStatus(String status,String email);
	public boolean updateUser(User userObj);
	public boolean deleteUser(User userObj);
	public User getUser(String email);
	public List<User> getUsers();

}
