package com.jwt.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.auth.TokenManager;
import com.jwt.example.entity.Login;

@RestController
@RequestMapping("/login")
public class AuthController {

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity<String> login(@RequestBody Login login) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
      return ResponseEntity.ok(tokenManager.getUserNameToken(login.getUsername()));
    } catch (Exception e) {
      throw e;
    }
  }
}
