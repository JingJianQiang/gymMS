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
	@RequestMapping("/adder")
	public String equipmentAdder() {
		return "EquipAdd";
	}
	
	@Autowired
	private EquipService equipService;
	
//	select操作
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<Equip> data(){
		return equipService.equipQueryAll();
	}
	
	 @RequestMapping(value = "/query/state", method = RequestMethod.GET)
	 @ResponseBody
	    public List<Equip> equipQueryByState() {
	        return equipService.equipQueryByState(false);
	    }
	 
	 @RequestMapping(value = "/query/id", method = RequestMethod.POST)
	 @ResponseBody
	    public List<Equip> equipQueryByID(@RequestBody Map<String,Object> data) {
		 	int equipID = Integer.parseInt( (String)data.get("equipID"));
		 	System.out.println("我要找id为"+ equipID);
		 	System.out.println("我找到了id为"+ equipID+"的"+equipService.equipQueryByID(equipID));
	        return equipService.equipQueryByID(equipID);
	    }
	 
	 @RequestMapping(value = "/query/type", method = RequestMethod.POST)
	 @ResponseBody
	    public List<Equip> equipQueryByType(@RequestBody Map<String,Object> data) {
		 	List<String> list = new ArrayList<String>();
		 	System.out.println("在接收你要查看的东西类型"+data.get("typeSelect"));
		 	list=(List<String>) data.get("typeSelect");
	        return equipService.equipQueryByType(list);
	    }
	 
	/* @RequestMapping(value = "/query/stateByID", method = RequestMethod.GET)
	 @ResponseBody
	    public Boolean equipQueryStateByID(@RequestBody Map<String,Object> data) {
		 	
	        return equipService.equipQueryStateByID(2);
	    }*/
	 
	 
//	 	update操作
//	 	预定器材
		@RequestMapping(value = "/order", method = RequestMethod.POST)
		@ResponseBody
		public ArrayList<Integer> equipOrder( @RequestBody Map<String,Object> data)  {
//			System.out.println("ajax进来了,我选择了"+data.get("selectList").getClass());	
			ArrayList selectList = (ArrayList) data.get("selectList");	
			return equipService.equipUpdateStateByID(selectList, true);
		}
		
		@RequestMapping(value = "/update",method = RequestMethod.POST)
		@ResponseBody
		public void equipUpdateByID(@RequestBody Map<String,Object> data) {
			Equip equip = new Equip();
			equip.setId(Integer.valueOf(data.get("equipID").toString()));
			equip.setType(data.get("equipType").toString());
			equip.setPrice(Integer.valueOf(data.get("equipPrice").toString()));
			equip.setIsSelect((Boolean)data.get("equipState"));
			equipService.equipUpdate(equip);
		}
//		@RequestMapping(value = "/update", method = RequestMethod.GET)
//		@ResponseBody
//		public Boolean equipStateChange( ) {	
//			equipService.equipUpdateStateByID(2, true);
//			return true;
//		}
		
//	 获取器材编号及名称的map:id - euqipType中的
	 @RequestMapping(value = "/query/typeMap", method = RequestMethod.GET)
	 @ResponseBody
    public List<EquipType> equipQueryTypeAll() {
        return equipService.euqipTypeQueryAll();
    }
	 
	 //delete操作
	 //根据id删除器材
	 @RequestMapping(value = "/delete/id")
	 @ResponseBody
	 public void equipDeleteByID( @RequestBody Map<String,Object> data) {
		 int equipID = (int) data.get("equipID");
		 equipService.equipDeleteByID(equipID);
	 }
	 
	 //insert
	 //添加器材
	 @RequestMapping(value = "/insert")
	 @ResponseBody
	 public void equipInsert( @RequestBody Map<String,Object> data) {
		 String type = data.get("equipType").toString();
		 int price = (Integer.valueOf(data.get("equipPrice").toString()));
		 equipService.equipInsert(type, price);
	 }
	 
}

