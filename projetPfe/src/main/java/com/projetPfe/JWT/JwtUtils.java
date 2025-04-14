package com.projetPfe.JWT;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.projetPfe.entities.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${projrtdon.app.jwtSecret}")
	private String jwtSecret;
	@Value("${projrtdon.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	private SecretKey getSigningKey() {
	    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateJwtToken(Authentication authentication) {
	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    System.out.println("userPrincipal ******* " + userPrincipal.getUsername());

	    return Jwts.builder()
	            .setSubject(userPrincipal.getUsername())
	            .setIssuedAt(new Date())
	            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
	            .compact();
	}


	public String getEmailFromJwtToken(String token) {
	    System.out.println("token ********* " + token);

	    Claims claims = Jwts.parserBuilder()
	                        .setSigningKey(getSigningKey())
	                        .build()
	                        .parseClaimsJws(token)
	                        .getBody();

	    System.out.println("claims.getSubject() --------------------------- " + claims.getSubject());

	    return claims.getSubject();
	}


	public boolean validateJwtToken(String authToken) {
	    try {
	        Jwts.parserBuilder()
	            .setSigningKey(getSigningKey())
	            .build()
	            .parseClaimsJws(authToken);
	        return true;
	    } catch (SecurityException e) {
	        logger.error("Invalid JWT signature: {}", e.getMessage());
	    }
	    catch (MalformedJwtException e) {
	        logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	        logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	        logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	        logger.error("JWT claims string is empty: {}", e.getMessage());
	    }
	    return false;
	}


}