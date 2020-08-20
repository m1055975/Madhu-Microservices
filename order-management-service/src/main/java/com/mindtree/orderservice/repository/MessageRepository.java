package com.mindtree.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.orderservice.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
