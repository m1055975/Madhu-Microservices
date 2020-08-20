package com.mindtree.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.userservice.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, String>{

}
