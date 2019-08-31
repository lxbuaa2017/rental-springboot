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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PdfContractController {
    @Autowired
    private PdfContractService pdfContractService;
    @Autowired
    private LongRentOrderRepository longRentOrderRepository;
    @Autowired
    private PdfFileRepository pdfFileRepository;
    @GetMapping(value = "/file/pdfContract",produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] makePdf(@RequestParam String orderid){
        LongRentOrder longRentOrder=longRentOrderRepository.findById(orderid).get();
        byte[] data=null;
        PdfFile pdfFile=new PdfFile();
        pdfFile.setName(longRentOrder.getTenantName()+"的合同");
        pdfFile.setLandLordName("青年房地产");
        pdfFile.setTenantName(longRentOrder.getTenantName());
        pdfFile.setRoom(longRentOrder.getRoom());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate begin1=LocalDate.parse(longRentOrder.getCheckInDay(),df);
        LocalDate end1=LocalDate.parse(longRentOrder.getLeaveDay(),df);
        LocalDateTime begin=begin1.atStartOfDay();
        LocalDateTime end=end1.atStartOfDay();
        int months=(int)((end1.toEpochDay()-begin1.toEpochDay())/30.0);
        pdfFile.setMonths(months);
        pdfFile.setStartDay(begin);
        pdfFile.setDueDay(end);
        PdfFile contract=pdfContractService.pdfContractMaking(pdfFile);
        PdfFile pdfFile1=pdfFileRepository.save(contract);
        String url="/api/file/getGontract/"+pdfFile1.getId();
        longRentOrder.setUrl(url);
        longRentOrderRepository.save(longRentOrder);
        return contract.getContent().getData();
    }
    @GetMapping(value = "file/getGontract/{id}",produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] downloadPdf(@PathVariable String id){
        byte[] data=null;
        PdfFile pdfFile=pdfFileRepository.findById(id).get();
        if(pdfFile!=null)
            data=pdfFile.getContent().getData();
        return data;
    }

    @GetMapping(value = "file/getAllContract")
    @ResponseBody
    public List<PdfFile> getAllContract(){
        return pdfFileRepository.findAll();
    }
}
