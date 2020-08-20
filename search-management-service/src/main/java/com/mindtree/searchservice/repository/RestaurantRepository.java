package com.mindtree.searchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.searchservice.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

}
