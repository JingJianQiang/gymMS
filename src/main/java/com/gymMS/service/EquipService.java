package com.gymMS.service;

import java.util.ArrayList;
import java.util.List;

import com.gymMS.domain.Equip;
import com.gymMS.domain.EquipType;

public interface EquipService {
	/*
	*查找器材
	*/
	List<Equip> equipQueryAll(); 
	List<Equip> equipQueryByState(Boolean isSelect);
	List<Equip> equipQueryByID(int equipID);
	List<Equip> equipQueryByType(List<String> equipType);
	Boolean equipQueryStateByID(int equipID);
	
	List<EquipType> euqipTypeQueryAll();
	
	ArrayList<Integer> equipUpdateStateByID(ArrayList<Integer> ids , Boolean state);
}