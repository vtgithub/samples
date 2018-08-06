package com.example.sse.ssetest;

import java.util.Date;

class StockTransaction {
    String user;
    Stock stock;
    Date when;


    public StockTransaction(String randomUser, Stock randomStock, Date date) {
        stock = randomStock;
        user= randomUser;
        when = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}