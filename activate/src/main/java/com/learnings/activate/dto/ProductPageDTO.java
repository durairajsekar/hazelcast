package com.learnings.activate.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageDTO  implements Serializable{
	private List<ProductDTO> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
}
