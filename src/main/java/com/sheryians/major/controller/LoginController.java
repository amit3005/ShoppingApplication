package com.sheryians.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleREPO;
import com.sheryians.major.repository.UserREPO;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private UserREPO userRepo;
	@Autowired
	private RoleREPO roleRepo;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/registerUser")
	public String registeruser(@ModelAttribute("user") User user , HttpServletRequest request) throws ServletException {
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		List<Role> roles = new  ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRolesList(roles);
		userRepo.save(user);
		return "redirect:/";
	}
}
