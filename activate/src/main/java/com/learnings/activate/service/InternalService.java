package com.learnings.activate.service;

import com.learnings.activate.dto.ProductRequest;
import com.learnings.activate.dto.ShopperProductRequest;

public interface InternalService {
	
	void addShopperData(ShopperProductRequest request);
	void addProductData(ProductRequest request);

}
