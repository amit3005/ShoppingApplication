package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.services.CategoryService;
import com.sheryians.major.services.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"/","/home"})
	public String home(ModelMap modelMap) {
		GlobalData.cart.clear();
		modelMap.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(ModelMap modelMap) {
		modelMap.addAttribute("cartCount", GlobalData.cart.size());
		modelMap.addAttribute("categories", categoryService.getAllCategory());
		modelMap.addAttribute("products",productService.getAllProduct());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String getProducts(ModelMap modelMap,@PathVariable("id") int id) {
		modelMap.addAttribute("cartCount", GlobalData.cart.size());
		modelMap.addAttribute("categories", categoryService.getAllCategory());
		modelMap.addAttribute("products",productService.findAllByCategoryid(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewproducts(ModelMap modelMap,@PathVariable("id") int id) {
		modelMap.addAttribute("cartCount", GlobalData.cart.size());
		modelMap.addAttribute("categories", categoryService.getAllCategory());
		modelMap.addAttribute("product",productService.getProductbyId(id));
		return "viewproduct";
	}
}
