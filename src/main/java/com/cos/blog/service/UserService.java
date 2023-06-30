package com.cos.blog.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional(readOnly = true)
	public Users 회원찾기(String username) {
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
	}

	
	@Transactional
	public void 회원가입(Users user) {

		String rawPassword = user.getPassword();//해쉬전 패스워드
		String encPassword = encoder.encode(rawPassword);//해쉬패스워드
		user.setPassword(encPassword);//패스워드에 해쉬패스워드 장착
		userRepository.save(user);
		user.setRole(RoleType.USER);
	}
	
	@Transactional
	public void 회원수정(Users user) {
		Users persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기  실패");
				
		});
		
		
		//validate 체크 = oauth필드에 값이 없으면 정보수정가능
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
	
		
		
	}
	
	

}
