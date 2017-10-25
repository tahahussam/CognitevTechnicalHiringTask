package com.cogn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cogn.entity.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByPhoneNumber(String phoneNumber);

	@SuppressWarnings("unchecked")
	User save(User user);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update User set password =:password where phoneNumber =:phoneNumber")
	void updateUser(@Param("password") String password, @Param("phoneNumber") String phoneNumber);
}