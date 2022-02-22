package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.services.CategoryService;
import com.sheryians.major.services.ProductService;

@Controller 
public class AdminController {

	private static String uploadPath = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin")
	public String admin() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String adminCategories(ModelMap modelMap) {
		List<Category> list = categoryService.getAllCategory();
		modelMap.addAttribute("categories", list);
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String CategorieAdd(ModelMap modelMap) {
		Category category=new Category();
		modelMap.addAttribute("category",category);
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/addCategory")
	public String addcategory(ModelMap modelMap,@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		modelMap.addAttribute("category",category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deletecategory(@PathVariable("id") int id) {
		categoryService.deletecategory(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updatecategory(@PathVariable("id") int id,ModelMap modelMap) {
		Category category=categoryService.getCategorybyId(id);
		modelMap.addAttribute("category",category);
		return "categoriesAdd";
	}
	
	@GetMapping("/admin/products")
	public String adminProducts(ModelMap modelMap) {
		List<Product> list = productService.getAllProduct();
		modelMap.addAttribute("products", list);
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAdd(ModelMap modelMap) {
		ProductDTO productDTO=new ProductDTO();
		List<Category> list = categoryService.getAllCategory();
		modelMap.addAttribute("categories", list);
		modelMap.addAttribute("productDTO",productDTO);
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/addproduct")
	public String addProduct(ModelMap modelMap,@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam("productImage") MultipartFile file,
							@RequestParam("imgName")String imgName) throws IOException {
		String imageName = "";
		if(!file.isEmpty()) {
			imageName=file.getOriginalFilename();
			Path path = Paths.get(uploadPath,imageName);
			Files.write(path,file.getBytes());
		}else {
			imageName=imgName;
		}
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setCategory(categoryService.getCategorybyId(productDTO.getCategoryId()));
		product.setDescription(productDTO.getDescription());
		product.setImageName(imageName);
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteproduct(@PathVariable("id") int id) {
		productService.deleteproduct(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateproduct(@PathVariable("id") int id,ModelMap modelMap) {
		Product productDTO=productService.getProductbyId(id);
		ProductDTO product= new ProductDTO();
		product.setCategoryId(productDTO.getCategory().getId());
		product.setDescription(productDTO.getDescription());
		product.setImageName(productDTO.getImageName());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setId(productDTO.getId());
		List<Category> list = categoryService.getAllCategory();
		modelMap.addAttribute("categories", list);
		modelMap.addAttribute("productDTO",product);
		return "productsAdd";
	}
}
