package com.projetPfe.implService;

import org.springframework.stereotype.Service;

import com.projetPfe.entities.UserDetailsImpl;
import com.projetPfe.entities.Utilisateur;
import com.projetPfe.repositories.UserRepositiry;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepositiry userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
		return UserDetailsImpl.build(user);
	}
}