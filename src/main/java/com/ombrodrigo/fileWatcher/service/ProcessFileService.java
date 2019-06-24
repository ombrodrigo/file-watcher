package com.ombrodrigo.fileWatcher.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.ombrodrigo.fileWatcher.service.ParseService;

public class ProcessFileService {

    private ParseService parseService;
    private String filesPath;

    public ProcessFileService (ParseService parseService, String filesPath) {
        this.parseService = parseService;
        this.filesPath = filesPath;
    }
    
    public void process()  {
        try {
            this.readFiles();
            this.writeResult();
        } catch (Exception e) {
        }
    }

    public String pathIn() {
        return this.filesPath + "/in/";
    }

    public String pathOut() {
        return this.filesPath + "/out/file.done.data";
    }

    private void readFiles() throws IOException  {
        try (Stream<Path> paths = Files.walk(Paths.get(this.filesPath))) {
            paths
                .filter(file -> file.toString().endsWith(".dat"))
                .forEach(file -> {
                    try {
                        parseService.parser(Files.readAllLines(file));
                    }  catch (Exception e) {
                    }
                });
        }
    }

    private void writeResult() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.pathOut()))) {

            String result = "Customers: " + parseService.getCustomers().size() + "\n" +
                            "Sellers: " +  parseService.getSellers().size();
            
            bufferedWriter.write(result);
        }
    }
}