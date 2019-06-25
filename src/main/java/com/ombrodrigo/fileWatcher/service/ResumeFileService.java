package com.ombrodrigo.fileWatcher.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ombrodrigo.fileWatcher.domain.Customer;
import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.domain.SaleItem;
import com.ombrodrigo.fileWatcher.domain.Seller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

@Service
public class ResumeFileService {

    public void resume(Path file, List<Seller> sellers, List<Customer> customers, List<Sale> sales) throws IOException {
        List<String> resumeList = new ArrayList<>();

        resumeList.add(this.numberCustomers(customers));
        resumeList.add(this.numberSellers(sellers));
        resumeList.add(this.moreExpensiveSale(sales));
        resumeList.add(this.worstSeller(sales));

        String resume = resumeList.stream().collect(Collectors.joining("\n"));

        Files.write(
            Paths.get(this.getOutputPath(file)),
            resume.getBytes()
        );
    }

    private String numberCustomers(List<Customer> customers) {
        return "Customers: " + customers.size();
    }

    private String numberSellers(List<Seller> sellers) {
        return "Sellers: " + sellers.size();
    }

    private String moreExpensiveSale(List<Sale> sales) {
        String moreExpensiveSaleId = null;
        Double moreExpensiveSalePrice = 0.0;
        
        for (Sale sale : sales) {
            Double priceSale = this.sumTotalItems(sale.getItems());

            if (priceSale > moreExpensiveSalePrice) {
                moreExpensiveSalePrice = priceSale;
                moreExpensiveSaleId = sale.getSaleId();
            }
        }
        
        return "MoreExpensiveSale: " + moreExpensiveSaleId;
    }

    private String worstSeller(List<Sale> sales) {
        String nameMoreExpensive = null;
        Double totalSaleWorstSeller = this.sumTotalItems(sales.get(0).getItems());

        for (Sale sale : sales) {
            Double priceSale = this.sumTotalItems(sale.getItems());

            if (priceSale < totalSaleWorstSeller) {
                nameMoreExpensive = sale.getSalesmanName();
                totalSaleWorstSeller = priceSale;
            }
        }

        return "WorstSeller: " + nameMoreExpensive;

    }

    private Double sumTotalItems(List<SaleItem> items)  {
        return items.stream()
            .map(item -> item.getPrice())
            .reduce(0.0, (itemA, itemB) -> itemA + itemB);
    }

    private String getOutputPath(Path file) {
        return String.format(
            System.getProperty("user.home") + "/data/out/%s.done.dat",
            FilenameUtils.removeExtension(file.getFileName().toString())
        );
    }
}