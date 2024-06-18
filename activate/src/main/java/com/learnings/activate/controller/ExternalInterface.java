package com.learnings.activate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.activate.dto.ProductPageDTO;
import com.learnings.activate.service.ExternalService;

@RestController
@RequestMapping("/products")
public class ExternalInterface {

	@Autowired
	private ExternalService externalService;

	@GetMapping("/shopper/{shopperId}")
	public ResponseEntity<ProductPageDTO> getProductsByShopper(@PathVariable("shopperId") String shopperId,
			@RequestParam(required = false) String category, @RequestParam(required = false) String brand,
			@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int page) {
		try {
			var size = limit > 100 ? 100 : limit;
			var productsByShopper = externalService.getProductsByShopper(shopperId, category, brand, page, size);
			return ResponseEntity.ok(productsByShopper);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
