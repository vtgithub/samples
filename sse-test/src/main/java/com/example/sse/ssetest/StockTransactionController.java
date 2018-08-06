package com.example.sse.ssetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/stock/transaction")
class StockTransactionController {
    @Autowired
    StockTransactionService stockTransactionService;

    @Autowired
    LoggedUser loggedUser;
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<StockTransaction> stockTransactionEvents(HttpSession httpSession){
                System.out.println(httpSession.getId());
        System.out.println(httpSession.getCreationTime());
        System.out.println(httpSession.getLastAccessedTime());
//        System.out.println(httpSession.getAttributeNames().toString());
                return stockTransactionService.getStockTransactions();

    }
}