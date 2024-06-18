package com.learnings.activate.service;

import com.learnings.activate.dto.ProductPageDTO;

public interface ExternalService {
	
	ProductPageDTO getProductsByShopper(String shopperId, String category, String brand, int page, int size);

}
