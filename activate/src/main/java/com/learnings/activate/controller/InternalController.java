package com.learnings.activate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.activate.dto.ProductRequest;
import com.learnings.activate.dto.ShopperProductRequest;
import com.learnings.activate.service.InternalService;

@RestController
@RequestMapping("/internal")
public class InternalController {
	

    @Autowired
    private InternalService internalService;

    @PostMapping("/shopper")
    public ResponseEntity<String> addShopperData(@RequestBody ShopperProductRequest request) {
        internalService.addShopperData(request);
        return ResponseEntity.ok("Shopper data added successfully");
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProductData(@RequestBody ProductRequest request) {
        internalService.addProductData(request);
        return ResponseEntity.ok("Product data added successfully");
    }
}
