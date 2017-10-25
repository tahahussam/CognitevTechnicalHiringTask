package com.cogn.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import com.cogn.common.Constants.EncryptionAlgorithmsNames;
import com.cogn.service.TokenHandler;

public class PasswordUtility {

	private final static Logger logger = LoggerFactory.getLogger(TokenHandler.class);

	public static String encrypt(String passWord) {
		try {
			Cipher encrypt = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE,
					new SecretKeySpec("x&24-R(|vH#lu-\"Oxv-'@8UI".getBytes(StandardCharsets.UTF_8),
							EncryptionAlgorithmsNames.ENCRYPTION_KEY_AGORITHM_NAME.getAlgorithmaName()),
					new IvParameterSpec("Z=7Xr7(k".getBytes(StandardCharsets.UTF_8)));
			byte[] encrypted = encrypt.doFinal(passWord.getBytes(StandardCharsets.UTF_8));
			return new String(Base64.encode(encrypted), StandardCharsets.UTF_8);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return passWord;
	}

	public static SecretKey getSecretKey(String encPassword) {
		long startTime = System.currentTimeMillis();
		logger.debug("generating new SecretKey");
		SecretKey secretKey = new SecretKeySpec(encPassword.getBytes(StandardCharsets.UTF_8),
				EncryptionAlgorithmsNames.SECRET_KEY_GENERATOR_ALGORITHM_NAME.getAlgorithmaName());
		logger.debug("generating done");
		logger.debug("time taken in generateSecretKey " + (System.currentTimeMillis() - startTime) + " miliSeconds");
		return secretKey;
	}
}
