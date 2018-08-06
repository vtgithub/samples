package com.example.sse.ssetest;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
class StockTransactionService {
    List<Stock> stockList = new ArrayList<>();
    Flux<StockTransaction> getStockTransactions() {
        System.out.println("entered new ____________");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        interval.subscribe((i) -> stockList.forEach(stock -> stock.setPrice(changePrice(stock.getPrice()))));
        Flux<StockTransaction> stockTransactionFlux = Flux.fromStream(Stream.generate(() -> new StockTransaction(getRandomUser(), getRandomStock(), new Date())));
        return Flux.zip(interval, stockTransactionFlux).map(Tuple2::getT2);
    }

    private Stock getRandomStock() {
        Random random = new Random();
        return new Stock(getRandomUser(), random.nextInt(10));
    }

    private String getRandomUser() {
        Random random = new Random();
        return "u"+ random.nextInt(10);
    }

    private float changePrice(float price) {

        return price+1;
    }
}