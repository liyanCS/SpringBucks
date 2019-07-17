package com.liyanCS.springbucks.repository;

import com.liyanCS.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Li Yan
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {

    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_name(String name);

}
