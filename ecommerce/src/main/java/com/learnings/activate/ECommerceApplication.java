package com.learnings.activate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	
	@Bean
	public HazelcastInstance getHazelCastInstance() {
		return Hazelcast.newHazelcastInstance();
	}

}
