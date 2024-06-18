package com.learnings.activate.util;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.learnings.activate.dto.ProductDTO;
import com.learnings.activate.dto.ProductPageDTO;
import com.learnings.activate.jpa.entity.ProductEntity;

@Component
public class ActivateUtil {
	
	public ProductPageDTO convertListToPageable(List<ProductDTO> output, Pageable pageable) {
		ProductPageDTO productPageDTO = new ProductPageDTO();
		if (output == null)
			return null;
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), output.size());

		List<ProductDTO> subList = output.subList(start, end);
		productPageDTO.setContent(subList);
		productPageDTO.setNumber(pageable.getPageNumber());
		productPageDTO.setSize(pageable.getPageSize());
		productPageDTO.setTotalElements(output.size());
		productPageDTO.setTotalPages((int) Math.ceil((double) output.size() / pageable.getPageSize()));
		return productPageDTO;
	}
	
	public ProductDTO convertProductEntityToDTO(ProductEntity productEntity) {
		ProductDTO dto = new ProductDTO();
		dto.setProductId(productEntity.getProductId());
		dto.setCategory(productEntity.getCategory());
		dto.setBrand(productEntity.getBrand());
		return dto;
	}

}
