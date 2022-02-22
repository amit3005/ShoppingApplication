package com.sheryians.major.configuearion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.CustomUserDetails;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.UserREPO;
import com.sun.el.stream.Optional;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserREPO userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User tblUserPass = userRepo.findUserByEmailId(username);
		if(tblUserPass == null) {
			throw new UsernameNotFoundException("User is Not Found Kindly Register first.");
		}else {
			return new CustomUserDetails(tblUserPass);
		}
	}

}
