package com.gymMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gymMS.domain.Equip;
import com.gymMS.domain.EquipType;
import com.gymMS.service.EquipService;

@RequestMapping("/equip")
@Controller
public class EquipController {
	
	@RequestMapping("")
    public String equipmentManager(ModelMap model) {
		return "EquipmentManager";
    }
	
	@Autowired
	private EquipService equipService;
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<Equip> data(){
		return equipService.equipQueryAll();
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public Boolean equipOrder( @RequestBody Map<String,Object> select)  {
		System.out.println("ajax进来了,我选择了"+select.get("selectList"));
//		ArrayList selectList = (ArrayList) select.get("selectList");
		return true;
	}
	
	
	 @RequestMapping(value = "/query/state", method = RequestMethod.GET)
	 @ResponseBody
	    public List<Equip> equipQueryByState() {
	        return equipService.equipQueryByState(false);
	    }
	 
	 @RequestMapping(value = "/query/id", method = RequestMethod.GET)
	 @ResponseBody
	    public List<Equip> equipQueryByID() {
	        return equipService.equipQueryByID(1);
	    }
	 
	 @RequestMapping(value = "/query/type", method = RequestMethod.GET)
	 @ResponseBody
	    public List<Equip> equipQueryByType() {
		 	List<String> list = new ArrayList<String>();
		 	list.add("篮球");
		 	list.add("羽毛球");
	        return equipService.equipQueryByType("篮球");
	    }
	 
//	 获取器材编号及名称的map
	 @RequestMapping(value = "/query/typeMap", method = RequestMethod.GET)
	 @ResponseBody
	    public List<EquipType> equipQueryTypeAll() {
	        return equipService.euqipTypeQueryAll();
	    }
}

