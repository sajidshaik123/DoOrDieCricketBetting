package com.cric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cric.model.User;


public interface IUserRepository extends CrudRepository<User, Integer>{

	User findByEmailAndIsActive(String email, boolean b);

	User findByPrimaryPhoneAndIsActive(String primaryPhone, boolean b);

	User findByEmailAndPasswordAndIsActive(String email, String password, boolean b);

	List<User> findByFullNameContainingAndIsActive(String search, boolean b);

}
