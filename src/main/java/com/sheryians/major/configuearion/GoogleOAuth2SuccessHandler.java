package com.sheryians.major.configuearion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.RedirectStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleREPO;
import com.sheryians.major.repository.UserREPO;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private RoleREPO roleRepo;
	@Autowired
	private UserREPO userRepo;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(userRepo.findUserByEmailId(email)==null) {
			User user = new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setEmailId(email);
			user.setLastName(token.getPrincipal().getAttributes().get("name").toString());
			List<Role> roles = new  ArrayList<>();
			roles.add(roleRepo.findById(2).get());
			user.setRolesList(roles);
			userRepo.save(user);
		}
		response.sendRedirect("/");
		return;
		//redirectStratergy.getRedirect(request,response,null);
		
	}
	
	

}
