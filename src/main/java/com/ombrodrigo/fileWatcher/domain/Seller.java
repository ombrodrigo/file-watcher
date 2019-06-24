package com.ombrodrigo.fileWatcher.domain;

public class Seller {

    private String id;
    private String cpf;
    private String name;
    private Double salary;

    public Seller() {
    }

    public Seller(String id, String cpf, String name, Double salary) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public Seller setId(String id) {
        this.id = id;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Seller setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getName() {
        return name;
    }

    public Seller setName(String name) {
        this.name = name;
        return this;
    }

    public Double getSalary() {
        return salary;
    }

    public Seller setSalary(Double salary) {
        this.salary = salary;
        return this;
    }
}