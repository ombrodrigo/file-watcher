package com.ombrodrigo.fileWatcher.parser;

import static org.junit.Assert.*;

import java.util.List;

import com.ombrodrigo.fileWatcher.domain.SaleItem;
import com.ombrodrigo.fileWatcher.fixture.DomainFixture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleItemParserTest {

    @Test
    public void parseLine() {
        String line = "[1-10-100,2-30-2.50,3-40-3.10]";

        SaleItemParser saleItemParser = new SaleItemParser();
        List<SaleItem> saleItems = saleItemParser.parse(line);

        List<SaleItem> expectedSaleItems = DomainFixture.getSaleItems();

        assertEquals(expectedSaleItems.get(1).getId(), saleItems.get(1).getId());
        assertEquals(expectedSaleItems.get(1).getQuantity(), saleItems.get(1).getQuantity());
        assertEquals(expectedSaleItems.get(1).getPrice(), saleItems.get(1).getPrice());
    }
}