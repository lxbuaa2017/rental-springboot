package com.rental.demo.controller;

import com.rental.demo.entity.File.PdfFile;
import com.rental.demo.entity.Room;
import com.rental.demo.repository.PdfFileRepository;
import com.rental.demo.service.PdfContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.DocumentType;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class PdfContractController {
    @Autowired
    private PdfContractService pdfContractService;
    @Autowired
    private PdfFileRepository pdfFileRepository;
    @GetMapping(value = "file/pdfContract",produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] makePdf(){
        byte[] data=null;
        PdfFile pdfFile=new PdfFile();
        pdfFile.setName("测试");
        pdfFile.setLandLordName("北航");
        pdfFile.setTenantName("我");
        Room room=new Room();
        room.setAddress("学院路37号");
        room.setArea("1000");
        pdfFile.setRoom(room);
        pdfFile.setMonths(24);
        pdfFile.setStartDay(LocalDateTime.now());
        pdfFile.setDueDay(LocalDateTime.now().plusMonths(24));
        PdfFile contract=pdfContractService.pdfContractMaking(pdfFile);
        return contract.getContent().getData();
    }
}
