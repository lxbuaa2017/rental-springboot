package com.rental.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImgService {
    public String uploadHouseImg(MultipartFile file, String address);
    public void removeAllImg(String address);
    public void removeOneImg(String address,String url);
    public String uploadImg(MultipartFile file);
}
