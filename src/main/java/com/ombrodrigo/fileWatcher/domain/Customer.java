package com.ombrodrigo.fileWatcher.domain;

public class Customer {

    private String id;
    private String cnpj;
    private String name;
    private String businessArea;

    public Customer() {
    }

    public Customer(String id, String cnpj, String name, String businessArea) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Customer setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public Customer setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
        return this;
    }
}