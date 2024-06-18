package com.learnings.activate.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.activate.jpa.entity.ShopperProductEntity;

public interface ShopperProductRepository extends JpaRepository<ShopperProductEntity, String> {
//    List<ShopperProductEntity> findByShopperIdAndCategoryAndBrand(String shopperId, String category, String brand, Pageable pageable);
}
