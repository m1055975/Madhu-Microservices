package com.mindtree.searchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.searchservice.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

}
