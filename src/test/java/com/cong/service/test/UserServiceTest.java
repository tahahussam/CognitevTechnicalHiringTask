package com.cong.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cong.dto.TokenDto;
import com.cong.service.UserService;

public class UserServiceTest {

	@Test
	public void generateTokenDtoTest() {
		TokenDto tokenDto = UserService.generateToken("01001234567", "password");
		assertThat(tokenDto).isNotNull();
		assertThat(tokenDto.isValid()).isTrue();
		assertThat(tokenDto.getToken()).isNotNull();
	}
}