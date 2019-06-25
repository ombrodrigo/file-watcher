package com.ombrodrigo.fileWatcher.watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.ombrodrigo.fileWatcher.service.ProcessFileService;

import org.springframework.stereotype.Component;

@Component
public class FileWatcher {

    private static ProcessFileService processFileService;
    
    public FileWatcher(ProcessFileService processFileService) {
        FileWatcher.processFileService = processFileService;
    }

    public static void run() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path inputDirectory = Paths.get(System.getProperty("user.home") + "/data/in");

        WatchKey watchKey = inputDirectory.register(
            watchService,
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY
        );

        while (true) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                Path file = inputDirectory.resolve((Path) event.context());
                processFile(file);
            } 
        }
    }

    private static void processFile(Path file) throws IOException {
        if (file.toString().endsWith(".dat")) {
            FileWatcher.processFileService.process(file);
        }
    }
}