package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepo extends JpaRepository<CustomExtension, Long>{

	void save(String extensionName);

}
