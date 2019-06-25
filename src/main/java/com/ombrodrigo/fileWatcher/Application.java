package com.ombrodrigo.fileWatcher;

import com.ombrodrigo.fileWatcher.watcher.FileWatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            FileWatcher.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
