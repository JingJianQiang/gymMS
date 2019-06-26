package com.gymMS.impl;

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
	public List<Equip> equipQueryByType(String equipType) {
		return equipDao.equipQueryByType(equipType);
	}

	public List<EquipType> euqipTypeQueryAll() {
		return equipDao.euqipTypeQueryAll();
	}
}