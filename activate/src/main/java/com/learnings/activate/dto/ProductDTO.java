package com.learnings.activate.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private String productId;
    private String category;
    private String brand;
}
