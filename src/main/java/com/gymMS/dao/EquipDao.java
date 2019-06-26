package com.gymMS.dao;
import  com.gymMS.domain.Equip;
import com.gymMS.domain.EquipType;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EquipDao {
//	查找全部器材
	@Select("select * from equip")
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
	@Select("select * from equip where EquipID like #{equipID}")
	@Results({
		@Result(property = "id", column="EquipID"),
		@Result(property = "type", column="EquipType"),
		@Result(property = "price", column="EquipPrice"),
		@Result(property = "isSelect", column="EquipState"),
	})
	List<Equip> equipQueryByID (@Param("equipID") int euqipID);
	
//	查找器材byType
	@Select("select * from equip where EquipType like #{equipType}")
	@Results({
		@Result(property = "id", column="EquipID"),
		@Result(property = "type", column="EquipType"),
		@Result(property = "price", column="EquipPrice"),
		@Result(property = "isSelect", column="EquipState"),
	})
	List<Equip> equipQueryByType (@Param("equipType") String euqipType);
	
//	@Select({
//		"<script>",
//			"select * from equip where EquipType in",
//			"<foreach collection='typeList' item='item' index='index' separator=','>",
//			"(	#{item})",
//			"</foreach>",
//		"</script>"
//	})
//	@Results({
//		@Result(property = "id", column="EquipID"),
//		@Result(property = "type", column="EquipType"),
//		@Result(property = "price", column="EquipPrice"),
//		@Result(property = "isSelect", column="EquipState"),
//	})
//	List<Equip> equipQueryByType (@Param("typeList") List<String> typeList);
	
//	 获取器材编号及名称的map
	@Select("select * from equipType")
	@Results({
		@Result(property = "id", column="EquipTypeID"),
		@Result(property = "name", column = "EquipTypeName")
	})
	List<EquipType> euqipTypeQueryAll();
}
