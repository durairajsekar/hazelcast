package com.learnings.activate.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRelevanceDTO implements Serializable{
	 private String productId;
	 private Double relevancyScore;
	

}
