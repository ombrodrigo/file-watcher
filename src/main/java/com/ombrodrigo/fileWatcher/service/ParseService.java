package com.ombrodrigo.fileWatcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ombrodrigo.fileWatcher.domain.Customer;
import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.domain.Seller;
import com.ombrodrigo.fileWatcher.parser.CustomerParser;
import com.ombrodrigo.fileWatcher.parser.SaleParser;
import com.ombrodrigo.fileWatcher.parser.SellerParser;

public class ParseService {

    private List<Seller> sellers = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    private SellerParser sellerParser;
    private CustomerParser customerParser;
    private SaleParser saleParser;

    public ParseService(SellerParser sellerParser, CustomerParser customerParser, SaleParser saleParser) {
        this.sellerParser = sellerParser;
        this.customerParser = customerParser;
        this.saleParser = saleParser;
    }    

    public void parser(List<String> lines) {
        lines.stream()
            .map(this::parseLine)
            .collect(Collectors.toList());
    }

    public List<Seller> getSellers() {
        return this.sellers;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    private String parseLine(String line) {
        if (sellerParser.isValidLine(line)) {
            sellers.add(sellerParser.parse(line));
        }

        if (customerParser.isValidLine(line)) {
            customers.add(customerParser.parse(line));
        }

        if (saleParser.isValidLine(line)) {
            sales.add(saleParser.parse(line));
        }

        return line;
    }
}