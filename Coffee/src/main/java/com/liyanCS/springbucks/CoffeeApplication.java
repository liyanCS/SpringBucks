package com.liyanCS.springbucks;

import com.liyanCS.springbucks.model.Coffee;
import com.liyanCS.springbucks.model.CoffeeOrder;
import com.liyanCS.springbucks.model.OrderStatus;
import com.liyanCS.springbucks.repository.CoffeeOrderRepository;
import com.liyanCS.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        findOrders();

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

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(OrderStatus.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order:{}", order);

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(OrderStatus.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order:{}", order);
    }

    private void findOrders() {
        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));


        List<CoffeeOrder> list = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));

        list = coffeeOrderRepository.findByCustomerOrderById("Li Lei");
        log.info("findByCustomerOrderById: {}", list);

        list.forEach(o -> {
            log.info("order:{}", o.getId());
            o.getItems().forEach(i -> log.info("Item: {}", i));
        });

        list = coffeeOrderRepository.findByItems_name("latte");
        log.info("findByItems_name:{}", getJoinedOrderId(list));
    }

    private String getJoinedOrderId(List<CoffeeOrder> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }

}
