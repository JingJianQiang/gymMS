package com.gymMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gymMS.pojo.RolePermission;

@Mapper
public interface RolePermissionMapper {
//	所有记录
	@Select("select * from role_permission")
	List<RolePermission> findAll();
	
//	根据权限查角色
	@Select("select * from role_permission where permission_id=#{permission_id}")
	public List<RolePermission> findRoleId(int permission_id);
	
//	根据角色查权限
	@Select("select * from role_permission where role_id=#{role_id}")
	public List<RolePermission> findPermission(int role_id);
	
//	根据角色查权限id
	@Select("select permission_id from role_permission where role_id=#{role_id}")
	public List<Integer> findPermissionId(int role_id);
	
//	添加记录
	@Insert("insert into role_permission (role_id,permission_id) values (#{role_id},#{permission_id})")
	public int addRecord(RolePermission rolePermission);
	
//	删除记录
	@Delete("delete from role_permission where id=#{id}")
	public void deleteById(int id);
	
//	根据角色id删除记录
	@Delete("delete from role_permission where role_id=#{role_id}")
	public void deleteByRoleId(int role_id);
	
//	根据权限id删除记录
	@Delete("delete from role_permission where permission_id=#{permission_id}")
	public void deleteByPermissionId(int permission_id);
	
//	更新记录
	@Update("updete role_permission set permission_id=#{permission_id} where role_id=#{role_id}")
	public int updatePermission(RolePermission rolePermission);
}
