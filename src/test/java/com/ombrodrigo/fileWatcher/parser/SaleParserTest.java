package com.ombrodrigo.fileWatcher.parser;

import static org.junit.Assert.*;

import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.fixture.DomainFixture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleParserTest {

    @Test
    public void parseLine() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        SaleParser saleParser = new SaleParser();
        Sale sale = saleParser.parse(line);
                        
        Sale saleExpected = DomainFixture.getSale();

        assertEquals(saleExpected.getId(), sale.getId());
        assertEquals(saleExpected.getSaleId(), sale.getSaleId());
        assertEquals(saleExpected.getSalesmanName(), sale.getSalesmanName());
        
        assertEquals(saleExpected.getItems().get(1).getId(), sale.getItems().get(1).getId());
        assertEquals(saleExpected.getItems().get(1).getQuantity(), sale.getItems().get(1).getQuantity());
        assertEquals(saleExpected.getItems().get(1).getPrice(), sale.getItems().get(1).getPrice());
    }
}