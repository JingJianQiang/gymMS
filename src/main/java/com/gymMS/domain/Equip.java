package com.gymMS.domain;

public class Equip{
	private Integer id ;
	private String type;
	private Integer price;
	private Boolean isSelect;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Boolean getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}
}
