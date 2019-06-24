package com.ombrodrigo.fileWatcher.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.ombrodrigo.fileWatcher.domain.Customer;
import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.domain.Seller;
import com.ombrodrigo.fileWatcher.fixture.DomainFixture;
import com.ombrodrigo.fileWatcher.parser.CustomerParser;
import com.ombrodrigo.fileWatcher.parser.SaleParser;
import com.ombrodrigo.fileWatcher.parser.SellerParser;
import com.ombrodrigo.fileWatcher.service.ParseService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParseServiceTest {

    @Test
    public void parseSeller() {
        List<String> lines = new ArrayList<>();
        lines.add("001ç1234567891234çPedroç50000");

        ParseService parseService = this.getParseService();

        parseService.parser(lines);

        Seller seller = parseService.getSellers().get(0);
        Seller sellerExpected = DomainFixture.getSeller();

        assertEquals(sellerExpected.getId(), seller.getId());
        assertEquals(sellerExpected.getCpf(), seller.getCpf());
        assertEquals(sellerExpected.getName(), seller.getName());
        assertEquals(sellerExpected.getSalary(), seller.getSalary());
    }

    @Test
    public void parseCustomer() {
        List<String> lines = new ArrayList<>();
        lines.add("002ç2345675433444345çEduardo PereiraçRural");

        ParseService parseService = this.getParseService();

        parseService.parser(lines);

        Customer customer = parseService.getCustomers().get(0);
        Customer customerExpected = DomainFixture.getCustomer();

        assertEquals(customerExpected.getId(), customer.getId());
        assertEquals(customerExpected.getCnpj(), customer.getCnpj());
        assertEquals(customerExpected.getName(), customer.getName());
        assertEquals(customerExpected.getBusinessArea(), customer.getBusinessArea());
    }

    @Test
    public void parseSale() {
        List<String> lines = new ArrayList<>();
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");

        ParseService parseService = this.getParseService();

        parseService.parser(lines);

        Sale sale = parseService.getSales().get(0);
        Sale saleExpected = DomainFixture.getSale();

        assertEquals(saleExpected.getId(), sale.getId());
        assertEquals(saleExpected.getSaleId(), sale.getSaleId());
        assertEquals(saleExpected.getSalesmanName(), sale.getSalesmanName());

        assertEquals(saleExpected.getItems().get(1).getId(), sale.getItems().get(1).getId());
        assertEquals(saleExpected.getItems().get(1).getQuantity(), sale.getItems().get(1).getQuantity());
        assertEquals(saleExpected.getItems().get(1).getPrice(), sale.getItems().get(1).getPrice());
    }

    @Test
    public void parseBatch() {
        List<String> lines = new ArrayList<>();
        lines.add("001ç1234567891234çPedroç50000");
        lines.add("001ç3245678865434çPauloç40000.99");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("002ç2345675433444345çEduardo PereiraçRural");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        lines.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

        ParseService parseService = this.getParseService();

        parseService.parser(lines);

        assertEquals(2, parseService.getSellers().size());
        assertEquals(2, parseService.getCustomers().size());
        assertEquals(2, parseService.getSales().size());
    }

    private ParseService getParseService() {
        return new ParseService(new SellerParser(), new CustomerParser(), new SaleParser());
    }
}