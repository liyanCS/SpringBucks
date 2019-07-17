package com.liyanCS.springbucks.service;

import com.liyanCS.springbucks.model.Coffee;
import com.liyanCS.springbucks.model.CoffeeOrder;
import com.liyanCS.springbucks.model.OrderStates;
import com.liyanCS.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Li Yan
 */
@Service
@Slf4j
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderStates.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New Order: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderStates states) {
        //状态机流转控制，订单状态只能朝大的方向流动，不能反方向流动
        if (states.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order:{}: {}", states, order.getState());
            return false;
        }
        order.setState(states);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
