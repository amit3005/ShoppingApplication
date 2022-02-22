package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.services.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductbyId(id));
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String cart(ModelMap modelMap) {
		modelMap.addAttribute("cartCount", GlobalData.cart.size());
		modelMap.addAttribute("cart", GlobalData.cart);
		double total=0.0;
		for(Product p:GlobalData.cart) {
			total = total+p.getPrice();
		}
		modelMap.addAttribute("total", total);
		return "cart";
	}
	
	@GetMapping("/cart//removeItem/{index}")
	public String removeCart(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(ModelMap modelMap) {
		double total=0.0;
		for(Product p:GlobalData.cart) {
			total = total+p.getPrice();
		}
		modelMap.addAttribute("total", total);
		return "checkout";
	}
}
