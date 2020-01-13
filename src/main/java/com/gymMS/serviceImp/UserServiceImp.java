package com.gymMS.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.UserMapper;
import com.gymMS.pojo.User;
import com.gymMS.service.UserService;

@Service
public class UserServiceImp  implements UserService{
	@Autowired UserMapper userMapper;

	@Override
	public List<User> list(){
		List<User> user =userMapper.findAll();
		return user;
	}

	@Override
	public User getByAccount(String user_account) {
		User user =userMapper.getUserByAccount(user_account);
		if(user==null) {
			return null;
		}
		return user;
	}

	@Override
	public User getByName(String user_name) {
		User user=userMapper.getUserByName(user_name);
		if(user==null)
			return null;
		return user;
	}

	@Override
	public void add(User user) {
		userMapper.saveUser(user);
	}

	@Override
	public void delete(int user_id) {
		userMapper.deleteUserById(user_id);;
	}

	@Override
	public void recover(int user_id) {
		userMapper.recoverUserById(user_id);	
	}

	@Override
	public User getUser(int user_id) {
		User user=userMapper.getUserById(user_id);
		return user;
	}

	@Override
	public void update(User user) {
		userMapper.updateById(user);
	}

	@Override
	public void changePassword(User user) {
		userMapper.changePasswordById(user);
	}

}
