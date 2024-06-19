package com.learnings.activate.jpa.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_RELEVANCE")
public class ProductRelevanceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "RELEVANCY_SCORE")
    private Double relevancyScore;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN) 
    @JoinColumn(name = "SHOPPER_PRODUCT_ID")
    private ShopperProductEntity shopperProduct;
}
