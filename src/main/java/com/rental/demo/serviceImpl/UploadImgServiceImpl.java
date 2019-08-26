package com.rental.demo.serviceImpl;

import com.rental.demo.entity.File.ImgFile;
import com.rental.demo.entity.Room;
import com.rental.demo.repository.ImgFileRepository;
import com.rental.demo.repository.RoomRepository;
import com.rental.demo.service.UploadImgService;
import com.rental.demo.util.Constant;
import com.rental.demo.util.JsonResult;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.rental.demo.util.Constant.ERROR;
import static com.rental.demo.util.Constant.SUCCESS;

@Service
public class UploadImgServiceImpl implements UploadImgService {
    @Autowired
    private ImgFileRepository imgFileRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public String uploadImg(MultipartFile file,String address) {
        if(file.isEmpty())
            return JsonResult.build(ERROR,"未选择图片",null);
        String jsonResult=null;
        String fileName=file.getOriginalFilename();
        try{
            ImgFile imgFile=new ImgFile();
            imgFile.setName(fileName);
            imgFile.setCreatedTime(LocalDateTime.now());
            imgFile.setContent(new Binary(file.getBytes()));
            imgFile.setContentType(file.getContentType());
            imgFile.setSize(file.getSize());
            ImgFile saveImg=imgFileRepository.save(imgFile);
            String url= "http://localhost:8081/file/image/"+saveImg.getId();
            Room room=roomRepository.findByAddress(address);
            List<String> list=new LinkedList<>();
            if(room.getImageUrls()==null){
               list.add(url);
               room.setImageUrls(list);
            }
            else {
                room.getImageUrls().add(url);
            }
            roomRepository.save(room);
            return JsonResult.build(SUCCESS,"上传成功",url);
        }
        catch (IOException e){
            e.printStackTrace();
            return JsonResult.build(ERROR,"发生IO异常，上传失败",null);
        }
    }
}
