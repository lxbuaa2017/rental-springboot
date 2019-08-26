package com.rental.demo.serviceImpl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.rental.demo.entity.File.PdfFile;
import com.rental.demo.repository.PdfFileRepository;
import com.rental.demo.service.PdfContractService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PdfContractServiceImpl implements PdfContractService {
    @Autowired
    private PdfFileRepository pdfFileRepository;
    @Override
    public PdfFile pdfContractMaking(PdfFile pdfFile) {
        PdfReader reader;
        //FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try{
            reader=new PdfReader("static/租房合同范本.pdf");
            bos=new ByteArrayOutputStream();
            stamper=new PdfStamper(reader,bos);
            AcroFields form=stamper.getAcroFields();
            form.setField("landLord",pdfFile.getLandLordName());
            form.setField("tenant",pdfFile.getTenantName());
            form.setField("address",pdfFile.getRoom().getAddress());
            form.setField("area",pdfFile.getRoom().getArea());
            form.setField("months",Integer.toString(pdfFile.getMonths()));
            form.setField("byYear",Integer.toString(pdfFile.getStartDay().getYear()));
            form.setField("byMonth",Integer.toString(pdfFile.getStartDay().getMonthValue()));
            form.setField("byDay",Integer.toString(pdfFile.getStartDay().getDayOfMonth()));
            form.setField("toYear",Integer.toString(pdfFile.getDueDay().getYear()));
            form.setField("toMonth",Integer.toString(pdfFile.getDueDay().getMonthValue()));
            form.setField("toDay",Integer.toString(pdfFile.getDueDay().getDayOfMonth()));
            String str=pdfFile.getTenantName()+"租房合同";
            form.setListOption(str,null,null);//这句没测试过，出bug的话删这句
            stamper.setFormFlattening(true);
            stamper.close();
            pdfFile.setContent(new Binary(bos.toByteArray()));
            pdfFile.setName(str);
            pdfFile.setCreatedTime(LocalDateTime.now());
            PdfFile savePdf=pdfFileRepository.save(pdfFile);
            return savePdf;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (DocumentException e){
            e.printStackTrace();;
        }
        return null;
    }
}
