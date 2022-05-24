package org.cite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMain {
    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);
    public static void main(String[] args) {
        logger.info("App main has started!!!");
        SpringApplication.run(AppMain.class);
    }
}
