package com.mechachieve.vinculus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechachieve.vinculus.infra.security.DadosTokenJWT;
import com.mechachieve.vinculus.infra.security.TokenService;
import com.mechachieve.vinculus.repositories.UserRepository;
import com.mechachieve.vinculus.user.AuthenticationDTO;
import com.mechachieve.vinculus.user.RegisterDTO;
import com.mechachieve.vinculus.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((User) auth.getPrincipal());


        return ResponseEntity.ok(new DadosTokenJWT(token));

    }

	 @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.nome(), data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
