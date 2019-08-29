package com.rental.demo.repository;

import com.rental.demo.entity.File.ImgFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgFileRepository extends MongoRepository<ImgFile,String> {
    public void deleteById(String id);
}
