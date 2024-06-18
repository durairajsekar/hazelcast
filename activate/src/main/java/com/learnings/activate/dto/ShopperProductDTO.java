package com.learnings.activate.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopperProductDTO {
    private String shopperId;
    private List<ProductRelevanceDTO> shelf;
}
