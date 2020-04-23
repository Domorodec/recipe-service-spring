package com.service.recipe.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.transaction.Transactional;

import com.service.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.service.recipe.model.User user = userService.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + user + " was not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<String> userRoles = Collections.singleton("ROLE_ADMIN");
        userRoles.forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRoles.toString()));
        });
        return new User(user.getName(), user.getPassword(), authorities);
    }

}