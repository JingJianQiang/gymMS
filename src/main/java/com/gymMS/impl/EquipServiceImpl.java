package com.gymMS.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymMS.dao.EquipDao;
import com.gymMS.domain.Equip;
import com.gymMS.domain.EquipType;
import com.gymMS.service.EquipService;

@Service
public class EquipServiceImpl implements EquipService {
	@Autowired
	private EquipDao equipDao;

//	查找全部
	public List<Equip> equipQueryAll() {
		return equipDao.equipQueryAll();
	}

//	按状态查找 0为空闲
	public List<Equip> equipQueryByState(Boolean isSelect) {
		return equipDao.equipQueryByState(isSelect);
	}

//	按id查找
	public List<Equip> equipQueryByID(int equipID) {
		return equipDao.equipQueryByID(equipID);
	}

//	按type查找
	public List<Equip> equipQueryByType(List<String> equipType) {
	 	List<Equip> currentList = new ArrayList<Equip>();
	 	List<Equip> totalList = new ArrayList<Equip>();
		for(int i = 0; i < equipType.size(); i++) {
			currentList = equipDao.equipQueryByType(equipType.get( i ));//currentList 存放当前查找的类型所对应的equipList
			for(int j = 0; j < currentList.size(); j++) {
				totalList.add( currentList.get(j));//把currentList逐个读入总查询结果
			}
		}
		return totalList;
//		return equipDao.equipQueryByType(equipType);
	}
	
	public Boolean equipQueryStateByID(int equipID) {
		return equipDao.equipQueryStateByID(equipID);
	}

	public List<EquipType> euqipTypeQueryAll() {
		return equipDao.euqipTypeQueryAll();
	}
	
	//update
	//根据ID系修改state
	public ArrayList<Integer> equipUpdateStateByID(ArrayList<Integer> ids , Boolean state) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i = 0; i < ids.size(); i++) {//检测此时是否被别人选中
			if(equipDao.equipQueryStateByID( (int)ids.get(i) ) == state) {
				results.add((int)ids.get(i));
				ids.remove(i);
				i--;
			}else {
				equipDao.equipUpdateStateByID( (int)ids.get(i), state);
			}
		}
		return results;
	}
	public void equipUpdate(Equip equip) {
		equipDao.equipUpdate(equip.getType(), equip.getPrice(), equip.getId());
	}
	
	//delete
	//根据ID删除器材
	public void equipDeleteByID(int id) {
		equipDao.equipDeleteByID(id);
	}
	
	//insert
	public void equipInsert(String type , int price) {
		equipDao.equipInsert(type, price);
	}
}