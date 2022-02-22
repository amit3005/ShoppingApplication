package com.sheryians.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.Product;

public interface ProductREPO extends JpaRepository<Product, Integer> {
	
	public List<Product> findAllByCategoryId(int id);

}
