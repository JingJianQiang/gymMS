package com.gymMS.dao;

import java.util.List;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gymMS.pojo.User;


@Mapper
public interface UserMapper {
//	列出所有用户
	@Select("select * from user")
	List<User> findAll();
//	用户注册	
	@Insert("insert into user (user_account,user_name,user_password,phone) values (#{user_account}"
			+ ",#{user_name},#{user_password},#{phone})")
	public int saveUser(User user);
	
//	删除用户
	@Update("update  user set isdel=1 where user_id = #{user_id}")
	public void deleteUserById(int user_id);
//	恢复用户
	@Update("update  user set isdel=0 where user_id = #{user_id}")
	public void recoverUserById(int user_id);
	
//	根据id找用户
	@Select("select * from user where user_id= #{user_id} ")
    public User getUserById(int user_id);
// 	根据账户找用户（登录）
	@Select("select * from user where user_account= #{user_account} ")
    public User getUserByAccount(String user_account);
// 	根据名字找用户   
	@Select("select * from user where user_name= #{user_name} ")
    public User getUserByName(String user_name);
// 	根据电话找用户   
	@Select("select * from user where phone= #{phone} ")
    public User getUserByPhone(String phone);
	
//	修改用户名，手机
    @Update("update user set user_name=#{user_name}, phone=#{phone} where user_id=#{user_id} ")
    public int updateById(User user); 
    
// 	修改密码
    @Update("update user set user_password=#{user_password} where user_id=#{user_id} ")
    public int changePasswordById(User user); 
    

}
