package com.jwt.example.auth;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  private Map<String, String> users = new HashMap<>();

  @PostConstruct
  public void init() {
    users.put("seyda", passwordEncoder.encode("123123"));
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (users.containsKey(username)) {
      return new User(username, users.get(username), new ArrayList<>());
    }
    throw new UsernameNotFoundException(username);
  }
}
