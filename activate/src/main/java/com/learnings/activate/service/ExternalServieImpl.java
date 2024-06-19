package com.learnings.activate.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.learnings.activate.dto.ProductDTO;
import com.learnings.activate.dto.ProductPageDTO;
import com.learnings.activate.jpa.entity.ProductEntity;
import com.learnings.activate.jpa.entity.ProductRelevanceEntity;
import com.learnings.activate.jpa.entity.ShopperProductEntity;
import com.learnings.activate.jpa.repository.ProductRepository;
import com.learnings.activate.jpa.repository.ShopperProductRepository;
import com.learnings.activate.util.ActivateUtil;
import com.learnings.activate.util.HazelcastCacheUtil;

@Service
public class ExternalServieImpl implements ExternalService {

	@Autowired
	HazelcastCacheUtil hazelcastCacheUtil;
	@Autowired
	ActivateUtil activateUtil;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ShopperProductRepository shopperProductRepository;

	@Autowired
	ProductRepository productRepository;

	/**
	 * This method retrieves shopper's product details with optional filters.
	 * Attempts to retrieve from hazelcast cache, if not found in cache attempts to
	 * retrieve from DB.
	 */
	@Override
	public ProductPageDTO getProductsByShopper(String shopperId, String category, String brand, int page, int size) {

		// Retrieves from hazelcast cache
		Pageable pageable = PageRequest.of(page, size);
		var cacheResult = hazelcastCacheUtil.getShopperProductCache(shopperId);
		if (cacheResult != null) {
			// Filtering the result for optionalFilters
			List<ProductDTO> filteredResult = cacheResult.stream()
					.filter(product -> (ObjectUtils.isEmpty(category) || product.getCategory().equals(category))
							&& (ObjectUtils.isEmpty(brand) || product.getBrand().equals(brand)))
					.collect(Collectors.toList());
			ProductPageDTO response = activateUtil.convertListToPageable(filteredResult, pageable);
			System.out.println(response);
			if (response != null) {
				return response;
			}
		}

		// If cache is not available for the input, try DB (If cache is not update at
		// the time of creation of record in DB, first hit would miss hazelcast cache)
		Optional<ShopperProductEntity> shopperProduct = shopperProductRepository.findById(shopperId);

		if (shopperProduct.isEmpty()) {
			return null;
		} else {
			// Update the cache so second hit would fetch from hazelcast cache
			hazelcastCacheUtil.updateShopperProductCache(shopperProduct.get());
		}

		return retrieveProductDetailsForFilters(category, brand, pageable, shopperProduct);
	}

	/**
	 * This method identifies relevant product details for shopper with filters and
	 * returns a pageable product details.
	 * 
	 */
	private ProductPageDTO retrieveProductDetailsForFilters(String category, String brand, Pageable pageable,
			Optional<ShopperProductEntity> shopperProduct) {
		List<ProductRelevanceEntity> shelf = shopperProduct.get().getShelf();
		List<String> productIds = shelf.stream().map(ProductRelevanceEntity::getProductId).collect(Collectors.toList());

		Page<ProductEntity> productsPage = productRepository.findAllByProductIdIn(productIds, pageable);

		List<ProductDTO> content = productsPage.getContent().stream()
				.filter(product -> (ObjectUtils.isEmpty(category) || product.getCategory().equals(category))
						&& (ObjectUtils.isEmpty(brand) || product.getBrand().equals(brand)))
				.map(product -> activateUtil.convertProductEntityToDTO(product)).collect(Collectors.toList());

		return new ProductPageDTO(content, productsPage.getTotalPages(), productsPage.getTotalElements(),
				productsPage.getSize(), productsPage.getNumber());
	}

}
