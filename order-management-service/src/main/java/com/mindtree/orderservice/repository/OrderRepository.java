package com.mindtree.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.orderservice.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
