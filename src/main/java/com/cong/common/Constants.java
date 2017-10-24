package com.cong.common;

public class Constants {

	public final static String IS_VALID_TOKEN_STR = "isValidToken";
	public final static String IS_EXPIRED_TOKEN_STR = "isExpiredToken";

	public enum EncryptionAlgorithmsNames {
		SECRET_KEY_GENERATOR_ALGORITHM_NAME("HmacSHA1"), ENCRYPTION_KEY_AGORITHM_NAME("DESede");

		private String algorithmaName;

		private EncryptionAlgorithmsNames(String algorithmaName) {
			this.algorithmaName = algorithmaName;
		}

		public String getAlgorithmaName() {
			return algorithmaName;
		}
	}
}