package org.adenaden.tutorial.jvm.workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.adenaden.tutorial.jvm.workshop.repository.BillRepository;
import org.adenaden.tutorial.jvm.workshop.repository.PaymentRepository;
import org.adenaden.tutorial.jvm.workshop.service.DokuService;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedIdentifyDTO;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedNotifyDTO;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedRedirectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DokuController
 */
@Controller
@RequestMapping("/doku")
@Slf4j
public class DokuController {

    @Autowired
    DokuService dokuService;

    @Autowired
    BillRepository tagihanDao;

    @Autowired
    PaymentRepository pembayaranDao;
    
    @GetMapping("/continue")
    public String kirimCustomerKeDoku(ModelMap model){
        return "doku";
    }

    @PostMapping(value = "/notify")
    @ResponseBody public String notify(DokuHostedNotifyDTO request){
        log.info(request.toString());
        dokuService.processNotify(request);
        return "CONTINUE";
    }

    //Controller untuk notify dari DOKU merchant
    @PostMapping(value = "/identify")
    @ResponseBody public String identify(DokuHostedIdentifyDTO request, Model model) {
        return "CONTINUE";
    }
    
    //Controller untuk menghandle redirect dari DOKU merchant
    @PostMapping("/redirect")
    public String redirect(DokuHostedRedirectDTO request) {
        log.info(request.toString());   
        // Pasang logic membaca redirect
        
        return "doku_redirect";
    }
    
}