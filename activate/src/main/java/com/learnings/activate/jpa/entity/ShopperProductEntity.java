package com.learnings.activate.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="SHOPPER_PRODUCT")
public class ShopperProductEntity {
	
	@Id
	@Column(name ="SHOPPER_ID")
    private String shopperId;
	
    @OneToMany(mappedBy = "shopperProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRelevanceEntity> shelf;
}
