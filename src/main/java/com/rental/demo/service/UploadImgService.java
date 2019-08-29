package com.rental.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImgService {
    public String uploadImg(MultipartFile file,String address);
    public void removeAllImg(String address);
    public void removeOneImg(String address,String url);
}
