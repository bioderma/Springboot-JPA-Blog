package com.cos.blog.config.auth;

import java.util.ArrayList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.cos.blog.model.Users;


import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{
	
	private Users user;
	
	public PrincipalDetail(Users user) {
		this.user=user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //계정이 만료되지않앗는지 리턴 true=만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정이 잠겨있지않앗는지 리턴(true :잠기지않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //비밀번호 만료여부 리턴 true = 만료안됌
		return true;
	}

	@Override
	public boolean isEnabled() {//계정활성화 리턴 true 해야 활성화

		return true;
	}
	
	@Override //계정권한
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 authorities.add(()->{return "ROLE_"+user.getRole();});
		 
		 return authorities;
	}

}
