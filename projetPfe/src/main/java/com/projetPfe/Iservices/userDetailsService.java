package com.projetPfe.Iservices;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface userDetailsService {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}