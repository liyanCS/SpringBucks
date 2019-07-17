package com.liyan.spring.data.mybatisdemo;

import com.liyan.spring.data.mybatisdemo.mapper.CoffeeMapper;
import com.liyan.spring.data.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Li Yan
 * 2019/7/14 18:22
 */
@SpringBootApplication
@MapperScan("com.liyan.spring.data.mybatisdemo.mapper")
@Slf4j
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Coffee coffee1 = Coffee.builder()
                .name("espresso1")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        Long id1 = coffeeMapper.save(coffee1);
        log.info("Coffee {} => {}", id1, coffee1);
        log.info("coffee1.getId:{}", coffee1.getId());

        Coffee coffee2 = Coffee.builder()
                .name("espresso2")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();

        Long id2 = coffeeMapper.save(coffee2);
        log.info("Coffee {} => {}", id2, coffee2);
        log.info("coffee2.getId:{}", coffee2.getId());

        Coffee coffee = coffeeMapper.findById(id1);
        log.info("Coffee {}", coffee);
    }


}
