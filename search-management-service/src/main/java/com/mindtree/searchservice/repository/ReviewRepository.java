package com.mindtree.searchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.searchservice.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String>{

}
