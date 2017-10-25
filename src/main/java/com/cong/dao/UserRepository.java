package com.cong.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cong.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findByPhoneNumber(String phoneNumber);

	User findByPhoneNumberAndPassword(String phoneNumber, String password);

	User save(User user);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update User set password =:password where phoneNumber =:phoneNumber")
	void updateUser(@Param("password") String password, @Param("phoneNumber") String phoneNumber);
}