package com.mechachieve.vinculus.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mechachieve.vinculus.user.User;


@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(User user) {
		try {
		    var algoritimo = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API Mechachieve.Vinculus")
		        .withSubject(user.getUsername())
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritimo);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar Token JWT!", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritimo = Algorithm.HMAC256(secret);
		    return JWT.require(algoritimo)
		        .withIssuer("API Mechachieve.Vinculus")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		} catch (JWTVerificationException exception){
		    // Invalid signature/claims
			throw new RuntimeException("Token JWT invalido ou Expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
