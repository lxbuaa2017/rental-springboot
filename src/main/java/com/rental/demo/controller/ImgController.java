package com.rental.demo.controller;

import com.rental.demo.entity.File.ImgFile;
import com.rental.demo.repository.ImgFileRepository;
import com.rental.demo.service.UploadImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class ImgController {
    @Autowired
    private UploadImgService uploadImgService;
    @Autowired
    private ImgFileRepository imgFileRepository;

    @PostMapping("/file/uploadHouseImage")
    public String uploadHouseImg(@RequestParam(value = "image") MultipartFile file,@RequestParam(value = "address") String address) {
        return uploadImgService.uploadHouseImg(file,address);
    }

    @PostMapping("/file/uploadImage")
    public String uploadImg(@RequestParam(value = "image") MultipartFile file) {
        return uploadImgService.uploadImg(file);
    }

    @GetMapping(value = "/file/image/{id}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] downloadImg(@PathVariable String id){
        byte[] data=null;
        ImgFile imgFile=imgFileRepository.findById(id).get();
        if(imgFile!=null)
            data=imgFile.getContent().getData();
        return data;
    }

    @PostMapping("/file/removeAllImage")
    public void removeAllImg(@RequestParam(value = "address") String address){
        uploadImgService.removeAllImg(address);
    }

    @PostMapping("/file/removeOneImage")
    public void removeOneImg(@RequestParam(value = "address") String address,@RequestParam(value = "url") String url){
        uploadImgService.removeOneImg(address,url);
    }
}
