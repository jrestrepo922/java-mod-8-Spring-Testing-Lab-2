package com.example.javamod8springtestinglab2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CryptoControllerUnitTest {
    @Test
    void shouldReturnSingleBitCoinPrice(){
        CryptoService cryptoService = Mockito.mock(CryptoService.class);
        String priceOfBitcoin = "23896.4597915312141578";
        CryptoController cryptoController = new CryptoController(cryptoService);
        //Anywhere the getCoinPrice() method is called will return priceOfBitcoin string
        when(cryptoService.getCoinPrice("bitcoin")).thenReturn(priceOfBitcoin);
        String expected = "The price of one bitcoin is 23896.4597915312141578";
        String actual = cryptoController.getCrypto("bitcoin");
        assertEquals(expected, actual);
    }

}
