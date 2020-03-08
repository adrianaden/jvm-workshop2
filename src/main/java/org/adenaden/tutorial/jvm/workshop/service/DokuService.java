package org.adenaden.tutorial.jvm.workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.adenaden.tutorial.jvm.workshop.entity.Bill;
import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.entity.Payment;
import org.adenaden.tutorial.jvm.workshop.entity.Registration;
import org.adenaden.tutorial.jvm.workshop.repository.BillRepository;
import org.adenaden.tutorial.jvm.workshop.repository.PaymentRepository;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedNotifyDTO;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedRequestDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DokuService
 */
@Slf4j
@Service
public class DokuService {

    @Value("${doku.merchantid}")
    private String merchantId;

    @Value("${doku.sharedkey}")
    private String sharedKey;

    @Value("${doku.url}")
    private String dokuUrl;

    @Autowired
    BillRepository tagihanDao;

    @Autowired
    PaymentRepository pembayaranDao;

    private DecimalFormat decFormat = new DecimalFormat("0.00");

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getDokuUrl(){
        return dokuUrl;
    }

    public DokuHostedRequestDTO createDokuRequest(Bill tagihan){
        Member p = tagihan.getRegistration().getMember();
        Registration pendaf = tagihan.getRegistration();

        DokuHostedRequestDTO request = DokuHostedRequestDTO.builder()
            .NAME(p.getName())
            .EMAIL(p.getEmail())
            .MALLID(merchantId).CHAINMERCHANT("NA")
            .AMOUNT(decFormat.format(pendaf.getCourse().getPrice()))
            .PURCHASEAMOUNT(decFormat.format(pendaf.getCourse().getPrice()))
            .TRANSIDMERCHANT(tagihan.getInvoiceNumber())
            .PAYMENTCHANNEL("")
            .REQUESTDATETIME(dateFormat.format(new Date()))
            .CURRENCY("360")
            .PURCHASECURRENCY("360")
            .SESSIONID("1234567890")
            .BASKET(tagihan.getDescription())
            .build();

        String words = DigestUtils.sha1Hex(request.getAMOUNT() + request.getMALLID() + sharedKey + request.getTRANSIDMERCHANT());
        request.setWORDS(words);
        
        return request;
    }

    public void processNotify(DokuHostedNotifyDTO request){
        String wordsComponent = "".concat(new DecimalFormat("0.00").format(request.getAMOUNT())).concat(merchantId).concat(sharedKey).concat(request.getTRANSIDMERCHANT()).concat(request.getRESULTMSG()).concat(request.getVERIFYSTATUS());
        if(request.getCURRENCY()!=null && !request.getCURRENCY().equals("360")){
            wordsComponent = wordsComponent.concat(request.getCURRENCY());
        }

        Boolean checkWords = DigestUtils.sha1Hex(wordsComponent).equals(request.getWORDS());
        
        if(checkWords){
            Bill t = tagihanDao.findByInvoiceNumber(request.getTRANSIDMERCHANT());
            Payment pembayaran = new Payment();
            pembayaran.setBill(t);
            pembayaran.setReference(request.getAPPROVALCODE());
            pembayaran.setPaymentTime(LocalDateTime.now());
            pembayaran.setDokuResponseCode(request.getRESPONSECODE());
            pembayaranDao.save(pembayaran);
            Boolean amountOk = t.getRegistration().getCourse().getPrice().equals(request.getAMOUNT());
            if("0000".equals(request.getRESPONSECODE()) && amountOk){
                t.setHasPaid(true);
                tagihanDao.save(t); 
            }
        }

    }


}