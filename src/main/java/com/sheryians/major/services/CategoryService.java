package com.sheryians.major.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.CategoryREPO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryREPO categoryRepo;
	
	public void addCategory(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> getAllCategory(){
		return categoryRepo.findAll();
	}
	
	public void updateCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public void deletecategory(int categoryid) {
		categoryRepo.deleteById(categoryid);
	}

	public Category getCategorybyId(int id) {
		return categoryRepo.findById(id).get();
	}
}
