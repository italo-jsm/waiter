package com.italo.waiter.service;

import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.SystemUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomUserDetailService implements UserDetailsService {

	private final SystemUserRepository systemUserRepository;
	Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

	@Autowired @Lazy
	public CustomUserDetailService(SystemUserRepository systemUserRepository) {
		this.systemUserRepository = systemUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser user = systemUserRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found"));
		String[] rolesAsString = new String[user.getRoles().size()];
		for(int i = 0 ; i < user.getRoles().size() ; i++) {
			rolesAsString[i] = user.getRoles().get(i).getName();
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.createAuthorityList(rolesAsString);
		User admin = new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		return admin;
	}

}
