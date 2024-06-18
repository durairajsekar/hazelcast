package com.learnings.activate.jpa.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PRODUCT")
public class ProductEntity {
	
	@Id
	@Column(name ="PRODUCT_ID")
	private String productId;
	
	@Column(name ="CATEGORY")
    private String category;
	
	@Column(name ="BRAND")
    private String brand;
}
