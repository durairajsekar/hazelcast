package com.learnings.activate.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learnings.activate.dto.ProductPageDTO;
import com.learnings.activate.dto.ProductRequest;
import com.learnings.activate.dto.ShopperProductRequest;
import com.learnings.activate.jpa.entity.ProductEntity;
import com.learnings.activate.jpa.entity.ProductRelevanceEntity;
import com.learnings.activate.jpa.entity.ShopperProductEntity;
import com.learnings.activate.jpa.repository.ProductRepository;
import com.learnings.activate.jpa.repository.ShopperProductRepository;
import com.learnings.activate.util.ActivateUtil;
import com.learnings.activate.util.HazelcastCacheUtil;

@Service
public class InternalServiceImpl implements InternalService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ShopperProductRepository shopperProductRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	HazelcastCacheUtil hazelcastCacheUtil;
	@Autowired
	ActivateUtil activateUtil;

	/**
	 *	This method adds shopper product details in DB and updates the details to hazelcast cache.
	 */
	@Override
	public void addShopperData(ShopperProductRequest request) {
		ShopperProductEntity shopperProductEntity = modelMapper.map(request, ShopperProductEntity.class);

		if (shopperProductEntity.getShelf() != null) {
			for (ProductRelevanceEntity productRelevance : shopperProductEntity.getShelf()) {
				productRelevance.setShopperProduct(shopperProductEntity);
			}
		}
		shopperProductRepository.save(shopperProductEntity);
		hazelcastCacheUtil.updateShopperProductCache(shopperProductEntity);
	}

	/**
	 *	This method adds product details in DB.
	 */
	@Override
	public void addProductData(ProductRequest request) {
		productRepository.save(modelMapper.map(request, ProductEntity.class));
	}

}
