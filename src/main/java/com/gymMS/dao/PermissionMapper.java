package com.gymMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gymMS.pojo.Permission;

@Mapper
public interface PermissionMapper {
//	所有权限
	@Select("select * from permission")
	List<Permission> findAll();
	
//	添加权限
	@Insert("inser into permission (permission_name,description) values (#{permission_name},#{description})")
	public int addPermission(Permission permission);
	
//	根据id查询权限
	@Select("select * from Permission where permission_id=#{permission_id}")
	public Permission getPermissionById(int permission_id);
	
//	根据id查询权限
	@Select("select permission_name from Permission where permission_id=#{permission_id}")
	public String getNameById(int permission_id);

//	根据name查询权限id
	@Select("select permission_id from Permission where permission_name=#{permission_name}")
	public int getIdByName(String permission_name);
	
	
//	更改权限
	@Update("update permission set permission_name=#{permission_name},"
			+ "description=#{description} where permission_id=#{permission_id}")
	public int changePermission(Permission permission);

//	更新描述
	@Update("update role set description=#{description} where permission_id=#{permission_id}")
	public int updateById(Permission permission);



}
