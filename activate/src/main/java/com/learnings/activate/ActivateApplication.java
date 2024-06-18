package com.learnings.activate;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.learnings.activate.dto.ProductRelevanceDTO;
import com.learnings.activate.jpa.entity.ProductRelevanceEntity;

@SpringBootApplication
public class ActivateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivateApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<ProductRelevanceDTO, ProductRelevanceEntity>() {
            @Override
            protected void configure() {
                skip().setId(null);
            }
        });
		return modelMapper;
	}
	
	@Bean
	public HazelcastInstance getHazelCastInstance() {
		return Hazelcast.newHazelcastInstance();
	}

}
