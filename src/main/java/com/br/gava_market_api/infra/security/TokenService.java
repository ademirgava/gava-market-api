package com.br.gava_market_api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.gava_market_api.domain.usuario.Usuario;



@Service
public class TokenService {

	@Value("{api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		System.out.println(secret);
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Game Stats")
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withExpiresAt(dataExpiracao())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token jwt", e);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("API Game Stats")
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTVerificationException e) {
			throw new RuntimeException("Token JWT inválido");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
