package com.gymMS.service;

import java.util.List;

import com.gymMS.pojo.User;

public interface UserService {
	
	public List<User> list();

	public User getByAccount(String user_account);

	public User getByName(String user_name);

	public void add(User user);

	public void delete(int user_id);
	
	public void recover(int user_id);

	public User getUser(int user_id);

	public void update(User user);
	
	public void changePassword(User user);
}
