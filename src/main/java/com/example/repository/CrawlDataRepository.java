package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.entities.CrawlData;

public interface CrawlDataRepository extends CrudRepository<CrawlData, Long> {

	
}
