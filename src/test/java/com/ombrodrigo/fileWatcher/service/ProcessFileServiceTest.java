package com.ombrodrigo.fileWatcher.service;

import static org.junit.Assert.*;

import com.ombrodrigo.fileWatcher.parser.CustomerParser;
import com.ombrodrigo.fileWatcher.parser.SaleParser;
import com.ombrodrigo.fileWatcher.parser.SellerParser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessFileServiceTest {

    @Test
    public void process() {

        ProcessFileService processFileService = this.getProcessFileService();

        try {
            processFileService.process();
        } catch (Exception e) {
        }
    }

    private ProcessFileService getProcessFileService() {
        return new ProcessFileService(
            new ParseService(
                new SellerParser(),
                new CustomerParser(),
                new SaleParser()
            ),
            this.getPathTestFiles()
        );
    }

    private String getPathTestFiles() {
        return System.getProperty("user.dir") + "/src/test/java/com/ombrodrigo/fileWatcher/data";
    }
}