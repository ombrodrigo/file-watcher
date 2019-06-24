package com.ombrodrigo.fileWatcher;

import com.ombrodrigo.fileWatcher.parser.CustomerParser;
import com.ombrodrigo.fileWatcher.parser.SaleParser;
import com.ombrodrigo.fileWatcher.parser.SellerParser;
import com.ombrodrigo.fileWatcher.service.ParseService;
import com.ombrodrigo.fileWatcher.service.ProcessFileService;

public class Watcher {

    public static void run() {
        getProcessFileService().process();
    }

    private static ProcessFileService getProcessFileService() {
        return new ProcessFileService(
            new ParseService(
                new SellerParser(),
                new CustomerParser(),
                new SaleParser()
            ),
            System.getProperty("user.home") + "/data"
        );
    }
}