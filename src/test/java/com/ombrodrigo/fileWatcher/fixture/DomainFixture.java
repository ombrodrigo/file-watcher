package com.ombrodrigo.fileWatcher.fixture;

import java.util.ArrayList;
import java.util.List;

import com.ombrodrigo.fileWatcher.domain.Customer;
import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.domain.SaleItem;
import com.ombrodrigo.fileWatcher.domain.Seller;

public class DomainFixture {

    public static Customer getCustomer() {
        return new Customer("002", "2345675433444345", "Eduardo Pereira", "Rural");
    }

    public static Seller getSeller() {
        return new Seller("001", "1234567891234", "Pedro", Double.parseDouble("50000"));
    }

    public static Sale getSale() {
        return new Sale("003", "10", getSaleItems(), "Pedro");
    }

    public static List<SaleItem> getSaleItems() {
        List<SaleItem> saleItems = new ArrayList<>();
        saleItems.add(new SaleItem("1", "10", Double.parseDouble("0")));
        saleItems.add(new SaleItem("2", "30", Double.parseDouble("2.50")));
        saleItems.add(new SaleItem("3", "40", Double.parseDouble("3.10")));
        return saleItems;
    }
}