package com.learnings.activate.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.learnings.activate.dto.ProductResponseDTO;
import com.learnings.activate.jpa.entity.ProductEntity;
import com.learnings.activate.jpa.entity.ProductRelevanceEntity;
import com.learnings.activate.jpa.entity.ShopperProductEntity;
import com.learnings.activate.jpa.repository.ProductRepository;

@Component
public class HazelcastCacheUtil {
	
	@Autowired
	HazelcastInstance hazelcastInstance;
	@Autowired
	ProductRepository productRepository;	
	@Autowired
	ModelMapper modelMapper;
	public void updateShopperProductCache(ShopperProductEntity shopperProductEntity) {
		try {
			List<ProductRelevanceEntity> shelf = shopperProductEntity.getShelf();
			// map having productId and relevanceScore for a shopper
			Map<String, Double> productRelevanceMap = shelf.stream()
	                .collect(Collectors.toMap(ProductRelevanceEntity::getProductId, ProductRelevanceEntity::getRelevancyScore));
			// Preparing a list of all product id for a shopper
			List<String> productIds = shelf.stream().map(ProductRelevanceEntity::getProductId)
					.collect(Collectors.toList());
			// Fetching other details of the products
			List<ProductEntity> products = productRepository.findAllByProductIdIn(productIds);
			// Merging the product details and relevance score and applying the filters
	        List<ProductResponseDTO> productRelevanceList = products.stream().map(product -> {
	            ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
	            dto.setRelevancyScore(productRelevanceMap.get(product.getProductId()));
	            return dto;
	        }).collect(Collectors.toList());
	        // Updating to hazelcast cache
	        IMap<String, List<ProductResponseDTO>> shopperProductMap = hazelcastInstance.getMap("shopperProduct");
			shopperProductMap.put(shopperProductEntity.getShopperId(),
					productRelevanceList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ProductResponseDTO> getShopperProductCache(String shopperId) {
		IMap<String, List<ProductResponseDTO>> shopperProductMap = hazelcastInstance.getMap("shopperProduct");
		List<ProductResponseDTO> results = shopperProductMap.get(shopperId);
		return results;
	}

}
