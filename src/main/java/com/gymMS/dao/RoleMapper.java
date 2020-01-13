package com.gymMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gymMS.pojo.Role;

@Mapper
public interface RoleMapper {
//	所有角色
	@Select("select * from role")
	List<Role> findAll(); 
	
//	添加角色
	@Insert("inser into role (role_name,description) values (#{role_name},#{description})")
	public int addRole(Role role);
	
//	根据id删除角色
	@Delete("delete from role where role_id=#{role_id}")
	public void deleteById(int role_id);
	
//	根据name删除角色
	@Delete("delete from role where role_name=#{role_name}")
	public void deleteByName(String role_name);
	
//	根据id查询角色
	@Select("select * from role where role_id=#{role_id}")
	public Role getRoleById(int role_id);
	
//	根据id查询角色
	@Select("select role_name from role where role_id=#{role_id}")
	public String getNameById(int role_id);
	
	
//	根据name查询角色
	@Select("select * from role where role_name=#{role_name}")
	public Role getRoleByName(String role_name);
	
//	根据name查询角色id
	@Select("select role_id from role where role_name=#{role_name}")
	public int getRoleId(String role_name);
	
//	更新角色描述
	@Update("update role set description=#{description} where role_id=#{role_id}")
	public int updateById(Role role);
}
