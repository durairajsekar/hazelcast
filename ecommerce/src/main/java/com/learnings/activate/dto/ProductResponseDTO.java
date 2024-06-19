package com.learnings.activate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDTO extends ProductDTO {
    private Double relevancyScore;
}
