package com.rental.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImgService {
    public String uploadImg(MultipartFile file);
}
