package com.mindtree.searchservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.searchservice.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, String>{

}