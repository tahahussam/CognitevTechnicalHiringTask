package com.cogn.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cogn.common.Constants;
import com.cogn.dto.TokenDto;
import com.cogn.util.PasswordUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	private final static Logger logger = LoggerFactory.getLogger(TokenHandler.class);

	public static TokenDto generateToken(String phoneNumber, String firstName, String password) {
		logger.debug("in generateToken with phoneNumber: " + phoneNumber + " and firstName: " + firstName);

		return new TokenDto("valid", firstName, phoneNumber, createJWT(phoneNumber, firstName, password));
	}

	public static TokenDto generateInvalidToken(String error) {
		return new TokenDto(error, null, null, null);
	}

	public static boolean isValidToken(String accessToken, String encPassword, String phoneNumber) {
		logger.debug("in isValidToken with accessToken: " + accessToken);
		if (accessToken == null || accessToken.isEmpty()) {
			logger.debug("accessToken is null or empty");
			return false;
		}

		try {
			Claims claims = Jwts.parser().setSigningKey(PasswordUtility.getSecretKey(encPassword).getEncoded())
					.parseClaimsJws(accessToken).getBody();

			logger.debug("phone number: " + claims.getId());
			logger.debug("first name: " + claims.getIssuer());
			logger.debug("Expiration of the token is: " + claims.getExpiration());

			return claims.getId().equals(phoneNumber);
		} catch (ExpiredJwtException expiredEx) {
			logger.error("ExpiredJwtException " + expiredEx.getMessage(), expiredEx);
			return false;
		} catch (Exception ex) {
			logger.error("Exception " + ex.getMessage(), ex);
			return false;
		}
	}

	// Sample method to construct a JWT
	private static String createJWT(String phoneNumber, String first_name, String encPassword) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		// We will sign our JWT with our the secret
		// byte[] apiKeySecretBytes
		// DatatypeConverter.parseBase64Binary(getTheSecretKeyValue());
		Key signingKey = new SecretKeySpec(PasswordUtility.getSecretKey(encPassword).getEncoded(),
				signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(phoneNumber).setIssuer(first_name).setIssuedAt(new Date(nowMillis))
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		Date exp = new Date(nowMillis + Constants.EXPIRED_TOKEN_TIME_MILI);
		builder.setExpiration(exp);

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
}