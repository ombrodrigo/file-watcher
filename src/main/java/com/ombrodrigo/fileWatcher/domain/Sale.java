package com.ombrodrigo.fileWatcher.domain;

import java.util.List;

import com.ombrodrigo.fileWatcher.domain.SaleItem;;

public class Sale {

    private String id;
    private String saleId;
    private List<SaleItem> items;
    private String salesmanName;

    public Sale(){
    }

    public Sale(String id, String saleId, List<SaleItem> items, String salesmanName) {
        this.id = id;
        this.saleId = saleId;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public String getId() {
        return id;
    }

    public Sale setId(String id) {
        this.id = id;
        return this;
    }

    public String getSaleId() {
        return saleId;
    }

    public Sale setSaleId(String saleId) {
        this.saleId = saleId;
        return this;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public Sale setItems(List<SaleItem> items) {
        this.items = items;
        return this;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public Sale setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
        return this;
    }
}