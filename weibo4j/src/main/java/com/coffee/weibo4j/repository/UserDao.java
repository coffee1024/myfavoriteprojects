package com.coffee.weibo4j.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coffee.weibo4j.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
