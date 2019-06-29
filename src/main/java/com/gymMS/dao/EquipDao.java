package com.gymMS.dao;
import  com.gymMS.domain.Equip;
import com.gymMS.domain.EquipType;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EquipDao {
	/**
	 * 以下皆为select操作
	 */
//	查找全部器材
	@Select("select * from equip ")
		@Results({
			@Result(property = "id", column="EquipID"),
			@Result(property = "type", column="EquipType"),
			@Result(property = "price", column="EquipPrice"),
			@Result(property = "isSelect", column="EquipState"),
		})
	List<Equip> equipQueryAll();
	
//	查找器材by状态 0为空闲
	@Select("select * from equip where EquipState+0 like #{isSelect} ")
	@Results({
		@Result(property = "id", column="EquipID"),
		@Result(property = "type", column="EquipType"),
		@Result(property = "price", column="EquipPrice"),
		@Result(property = "isSelect", column="EquipState"),
	})
	List<Equip> equipQueryByState(@Param("isSelect") Boolean isSelect);
	
//	查找器材byID
	@Select("select * from equip where EquipID regexp #{equipID}  ")
	@Results({
		@Result(property = "id", column="EquipID"),
		@Result(property = "type", column="EquipType"),
		@Result(property = "price", column="EquipPrice"),
		@Result(property = "isSelect", column="EquipState"),
	})
	List<Equip> equipQueryByID (@Param("equipID") int euqipID);
	
//	查找器材byType
	@Select("select * from equip where EquipType regexp #{equipType} ")
	@Results({
		@Result(property = "id", column="EquipID"),
		@Result(property = "type", column="EquipType"),
		@Result(property = "price", column="EquipPrice"),
		@Result(property = "isSelect", column="EquipState"),
	})
	List<Equip> equipQueryByType (@Param("equipType") String euqipType);
	 
	@Select("select EquipState+0 from equip where EquipID like #{equipID}")
	@Result(property = "state",column ="EquipState")
	Boolean equipQueryStateByID(@Param("equipID") int equipID);
	
//	 获取器材编号及名称的map
	@Select("select * from equipType")
	@Results({
		@Result(property = "id", column="EquipTypeID"),
		@Result(property = "name", column = "EquipTypeName")
	})
	List<EquipType> euqipTypeQueryAll();
	
	/**
	 * 以下为update操作
	 */
	@Update("update equip set EquipState = #{setState} where EquipID like #{equipID}")
	void equipUpdateStateByID(@Param("equipID") int id , @Param("setState") Boolean state );
}
