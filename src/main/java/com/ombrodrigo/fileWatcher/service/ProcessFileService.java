package com.ombrodrigo.fileWatcher.service;

import java.nio.file.Files;
import java.nio.file.Path;

import com.ombrodrigo.fileWatcher.service.ParseService;
import com.ombrodrigo.fileWatcher.service.ResumeFileService;

import org.springframework.stereotype.Service;

@Service
public class ProcessFileService {

    private ParseService parseService;
    private ResumeFileService resumeFileService;

    public ProcessFileService (ParseService parseService, ResumeFileService resumeFileService) {
        this.parseService = parseService;
        this.resumeFileService = resumeFileService;
    }

    public void process(Path file)  {
        try {
            parseService.parser(Files.readAllLines(file));
            resumeFileService.resume(
                file,
                parseService.getSellers(),
                parseService.getCustomers(),
                parseService.getSales()
            );
        } catch (Exception e) {
        }
    }
}