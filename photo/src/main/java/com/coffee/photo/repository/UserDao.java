package com.coffee.photo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coffee.photo.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
