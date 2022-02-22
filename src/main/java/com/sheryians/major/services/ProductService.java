package com.sheryians.major.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductREPO;

@Service
public class ProductService {
	
	@Autowired
	private ProductREPO productRepo;
	
	public void addProduct(Product product) {
		productRepo.save(product);
	}

	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}
	
	public void updateProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteproduct(int productid) {
		productRepo.deleteById(productid);
	}

	public Product getProductbyId(int id) {
		return productRepo.findById(id).get();
	}
	
	public List<Product> findAllByCategoryid(int id){
		List<Product> list = productRepo.findAllByCategoryId(id);
		return list;
	}

}
