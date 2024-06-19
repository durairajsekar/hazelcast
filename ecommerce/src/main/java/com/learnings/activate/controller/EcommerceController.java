package com.learnings.activate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.learnings.activate.dto.ProductResponseDTO;

@RestController
@RequestMapping("/product")
public class EcommerceController {

	
	@Autowired
	HazelcastInstance hazelcastInstance;
	
	@GetMapping("/shopper/{shopperId}")
	public ResponseEntity<List<ProductResponseDTO>> getProductsByShopper(@PathVariable("shopperId") String shopperId) {
		try {
			IMap<String, List<ProductResponseDTO>> shopperProductMap = hazelcastInstance.getMap("shopperProduct");
			return ResponseEntity.ok(shopperProductMap.get(shopperId)) ;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
}
