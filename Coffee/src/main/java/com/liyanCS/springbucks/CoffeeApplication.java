package com.liyanCS.springbucks;

import com.liyanCS.springbucks.model.Coffee;
import com.liyanCS.springbucks.model.CoffeeOrder;
import com.liyanCS.springbucks.model.OrderStates;
import com.liyanCS.springbucks.repository.CoffeeOrderRepository;
import com.liyanCS.springbucks.repository.CoffeeRepository;
import com.liyanCS.springbucks.service.CoffeeOrderService;
import com.liyanCS.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Li Yan
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class CoffeeApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        initOrders();

        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderStates.PAID));
            log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderStates.INIT));
        }

    }

    private void initOrders() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee:{}", espresso);

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee:{}", latte);

        Coffee mocha = Coffee.builder()
                .name("mocha")
                .price(Money.of(CurrencyUnit.of("CNY"), 35.0))
                .build();
        coffeeRepository.save(mocha);
        log.info("Coffee:{}", mocha);

    }

}
