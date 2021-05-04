package com.roman.hermes;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerService {
    @Autowired
    RomanUrlValidator urlValidator;
    @Autowired
    BuyerWorkerBean buyerWorkerBean;
    @GetMapping("/buy")
    public String buy(@RequestParam(value = "url") String url){
        if (!urlValidator.isValid(url))
            throw  new IllegalArgumentException(url + " is invalid");

         return  buyerWorkerBean.buy(url);
    }
}
