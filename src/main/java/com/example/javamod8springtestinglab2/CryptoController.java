package com.example.javamod8springtestinglab2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {
    private CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService){
        this.cryptoService = cryptoService;
    }

    @GetMapping("/crypto")
    public String getCrypto(){
        String cryptoPrice = cryptoService.getCoinPrice();
        return "The price of one Bitcoin is " + cryptoPrice;
    }

}
