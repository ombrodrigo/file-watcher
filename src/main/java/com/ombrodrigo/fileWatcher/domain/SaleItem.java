package com.ombrodrigo.fileWatcher.domain;

public class SaleItem {

    private String id;
    private String quantity;
    private Double price;

    public SaleItem(){
    }

    public SaleItem(String id, String quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public SaleItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public SaleItem setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public SaleItem setPrice(Double price) {
        this.price = price;
        return this;
    }
}