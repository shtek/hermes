package com.roman.hermes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BuyerWorkerBeanTest {
    @Autowired
    BuyerWorkerBean buyerWorkerBean;
    @Test
    void buy() {
       String url = "https://www.hermes.com/uk/en/product/bolide-1923-30-craft-bag-H073491CKAA/";
       // String url = "https://www.hermes.com";

        System.out.println(buyerWorkerBean.buy(url));
    }
}