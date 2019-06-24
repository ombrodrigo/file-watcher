package com.ombrodrigo.fileWatcher.parser;

import static org.junit.Assert.*;

import com.ombrodrigo.fileWatcher.domain.Customer;
import com.ombrodrigo.fileWatcher.fixture.DomainFixture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerParserTest {

    @Test
    public void parseLine() {
        String line = "002ç2345675433444345çEduardo PereiraçRural";

        CustomerParser customerParser = new CustomerParser();
        Customer customer = customerParser.parse(line);
        
        Customer customerExpected = DomainFixture.getCustomer();

        assertEquals(customerExpected.getId(), customer.getId());
        assertEquals(customerExpected.getCnpj(), customer.getCnpj());
        assertEquals(customerExpected.getName(), customer.getName());
        assertEquals(customerExpected.getBusinessArea(), customer.getBusinessArea());
    }
}