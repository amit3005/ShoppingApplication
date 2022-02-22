package com.sheryians.major.dto;

public class ProductDTO {
	
	private int id;
	private String name;
	private int categoryId;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double prize) {
		this.price = prize;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String stringName) {
		this.imageName = stringName;
	}
	@Override
	public String toString() {
		return "ProductDatabean [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", prize=" + price
				+ ", weight=" + weight + ", description=" + description + ", stringName=" + imageName + "]";
	}

}
