package com.learnings.activate.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.activate.jpa.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

	Page<ProductEntity> findAllByProductIdIn(List<String> productIds, Pageable pageable);
	
	List<ProductEntity> findAllByProductIdIn(List<String> productIds);
}
