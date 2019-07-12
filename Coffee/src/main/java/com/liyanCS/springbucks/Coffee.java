package com.liyanCS.springbucks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Li Yan
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class Coffee {

    public static void main(String[] args) {
        SpringApplication.run(Coffee.class, args);
    }

}
