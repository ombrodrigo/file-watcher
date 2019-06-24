package com.ombrodrigo.fileWatcher.parser;

import static org.junit.Assert.*;

import com.ombrodrigo.fileWatcher.domain.Seller;
import com.ombrodrigo.fileWatcher.fixture.DomainFixture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SellerParserTest {

    @Test
    public void parseLine() {
        String line = "001ç1234567891234çPedroç50000";

        SellerParser sellerParser = new SellerParser();
        Seller seller = sellerParser.parse(line);
        
        Seller sellerExpected = DomainFixture.getSeller();

        assertEquals(sellerExpected.getId(), seller.getId());
        assertEquals(sellerExpected.getCpf(), seller.getCpf());
        assertEquals(sellerExpected.getName(), seller.getName());
        assertEquals(sellerExpected.getSalary(), seller.getSalary());
    }
}

