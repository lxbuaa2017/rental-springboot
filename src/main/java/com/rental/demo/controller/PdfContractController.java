package com.rental.demo.controller;

import com.rental.demo.entity.File.PdfFile;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.Room;
import com.rental.demo.repository.LongRentOrderRepository;
import com.rental.demo.repository.PdfFileRepository;
import com.rental.demo.service.PdfContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.DocumentType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class PdfContractController {
    @Autowired
    private PdfContractService pdfContractService;
    @Autowired
    private LongRentOrderRepository longRentOrderRepository;
    @Autowired
    private PdfFileRepository pdfFileRepository;
    @GetMapping(value = "file/pdfContract",produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] makePdf(@RequestParam String orderid){
        LongRentOrder longRentOrder=longRentOrderRepository.findById(orderid).get();
        byte[] data=null;
        PdfFile pdfFile=new PdfFile();
        pdfFile.setName(longRentOrder.getTenantName()+"的合同");
        pdfFile.setLandLordName("青年房地产有限公司");
        pdfFile.setTenantName(longRentOrder.getTenantName());
        pdfFile.setRoom(longRentOrder.getRoom());
        LocalDateTime begin=LocalDateTime.parse(longRentOrder.getCheckInDay(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime end=LocalDateTime.parse(longRentOrder.getLeaveDay(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int months=(int)Math.ceil((end.toLocalDate().toEpochDay()-begin.toLocalDate().toEpochDay())/30.0);
        pdfFile.setMonths(months);
        pdfFile.setStartDay(begin);
        pdfFile.setDueDay(end);
        PdfFile contract=pdfContractService.pdfContractMaking(pdfFile);
        pdfFileRepository.save(contract);
        longRentOrder.setPdffile(contract);
        return contract.getContent().getData();
    }
}
