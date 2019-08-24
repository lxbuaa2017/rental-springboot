package com.rental.demo.repository;

import com.rental.demo.entity.File.PdfFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfFileRepository extends MongoRepository<PdfFile,String> {
}
