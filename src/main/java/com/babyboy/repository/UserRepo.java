package com.babyboy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babyboy.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
