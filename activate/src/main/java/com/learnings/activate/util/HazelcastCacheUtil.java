package com.learnings.activate.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.learnings.activate.dto.ProductDTO;
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
			List<String> productIds = shelf.stream().map(ProductRelevanceEntity::getProductId)
					.collect(Collectors.toList());

			List<ProductEntity> products = productRepository.findAllByProductIdIn(productIds);
			IMap<String, List<ProductDTO>> shopperProductMap = hazelcastInstance.getMap("shopperProduct");
			shopperProductMap.put(shopperProductEntity.getShopperId(),
					modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
					}.getType()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ProductDTO> getShopperProductCache(String shopperId) {
		IMap<String, List<ProductDTO>> shopperProductMap = hazelcastInstance.getMap("shopperProduct");
		List<ProductDTO> results = shopperProductMap.get(shopperId);
		return results;
	}

}
