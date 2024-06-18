package com.learnings.activate.mapper;

import com.learnings.activate.dto.ProductDTO;
import com.learnings.activate.dto.ProductRelevanceDTO;
import com.learnings.activate.dto.ShopperProductDTO;
import com.learnings.activate.jpa.entity.ProductEntity;
import com.learnings.activate.jpa.entity.ProductRelevanceEntity;
import com.learnings.activate.jpa.entity.ShopperProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T23:10:13+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MapperStructImpl implements MapperStruct {

    @Override
    public ShopperProductEntity shopperProductDTOToEntity(ShopperProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ShopperProductEntity shopperProductEntity = new ShopperProductEntity();

        shopperProductEntity.setShopperId( dto.getShopperId() );
        shopperProductEntity.setShelf( productRelevanceDTOListToProductRelevanceEntityList( dto.getShelf() ) );

        return shopperProductEntity;
    }

    @Override
    public ShopperProductDTO shopperProductEntityToDTO(ShopperProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ShopperProductDTO shopperProductDTO = new ShopperProductDTO();

        shopperProductDTO.setShopperId( entity.getShopperId() );
        shopperProductDTO.setShelf( productRelevanceEntityListToProductRelevanceDTOList( entity.getShelf() ) );

        return shopperProductDTO;
    }

    @Override
    public ProductEntity ProductDTOTotoEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId( dto.getProductId() );
        productEntity.setCategory( dto.getCategory() );
        productEntity.setBrand( dto.getBrand() );

        return productEntity;
    }

    @Override
    public ProductDTO ProductEntityToDTO(ProductDTO entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId( entity.getProductId() );
        productDTO.setCategory( entity.getCategory() );
        productDTO.setBrand( entity.getBrand() );

        return productDTO;
    }

    protected ProductRelevanceEntity productRelevanceDTOToProductRelevanceEntity(ProductRelevanceDTO productRelevanceDTO) {
        if ( productRelevanceDTO == null ) {
            return null;
        }

        ProductRelevanceEntity productRelevanceEntity = new ProductRelevanceEntity();

        productRelevanceEntity.setProductId( productRelevanceDTO.getProductId() );
        productRelevanceEntity.setRelevancyScore( productRelevanceDTO.getRelevancyScore() );

        return productRelevanceEntity;
    }

    protected List<ProductRelevanceEntity> productRelevanceDTOListToProductRelevanceEntityList(List<ProductRelevanceDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductRelevanceEntity> list1 = new ArrayList<ProductRelevanceEntity>( list.size() );
        for ( ProductRelevanceDTO productRelevanceDTO : list ) {
            list1.add( productRelevanceDTOToProductRelevanceEntity( productRelevanceDTO ) );
        }

        return list1;
    }

    protected ProductRelevanceDTO productRelevanceEntityToProductRelevanceDTO(ProductRelevanceEntity productRelevanceEntity) {
        if ( productRelevanceEntity == null ) {
            return null;
        }

        ProductRelevanceDTO productRelevanceDTO = new ProductRelevanceDTO();

        productRelevanceDTO.setProductId( productRelevanceEntity.getProductId() );
        productRelevanceDTO.setRelevancyScore( productRelevanceEntity.getRelevancyScore() );

        return productRelevanceDTO;
    }

    protected List<ProductRelevanceDTO> productRelevanceEntityListToProductRelevanceDTOList(List<ProductRelevanceEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductRelevanceDTO> list1 = new ArrayList<ProductRelevanceDTO>( list.size() );
        for ( ProductRelevanceEntity productRelevanceEntity : list ) {
            list1.add( productRelevanceEntityToProductRelevanceDTO( productRelevanceEntity ) );
        }

        return list1;
    }
}
