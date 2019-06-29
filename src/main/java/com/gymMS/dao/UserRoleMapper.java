package com.gymMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gymMS.pojo.UserRole;

@Mapper
public interface UserRoleMapper {
//	所有记录
	@Select("select * from user_role")
	List<UserRole> findAll();
//	根据角色查用户
	@Select("select * from user_role where role_id=#{role_id}")
	public List<UserRole>  findUser(int role_id);
//	根据用户查角色
	@Select("select * from user_role where user_id=#{user_id}")
	public List<UserRole>  findRole(int user_id);
//	添加记录
	@Insert("insert into user_role (user_id,role_id) values (#{user_id},#{role_id})")
	public int addRecord(UserRole userRole);
//	删除记录
	@Delete("delete from user_role where id=#{id}")
	public void deleteById(int id);
	
//	根据用户id删除记录
	@Delete("delete from user_role where user_id=#{user_id}")
	public void deleteByUserId(int user_id);
	
//	根据用户查角色
	@Select("select  IFNULL(MAX(role_id),0) from user_role where user_id=#{user_id}")
	public int findRoleId(int user_id);
	
//	角色删除
	@Update("update  user_role set isdel=1 where user_id = #{user_id}")
	public void delete(int user_id);
//	角色恢复
	@Update("update  user_role set isdel=0 where user_id = #{user_id}")
	public void recover(int user_id);
	
//	更新记录
	@Update("updete user_role set role_id=#{role_id} where user_id=#{user_id}")
	public int updateRole(UserRole userRole);
	
	
}
