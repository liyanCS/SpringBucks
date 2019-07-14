package com.liyanCS.springbucks.repository;

import com.liyanCS.springbucks.model.CoffeeOrder;

import java.util.List;

/**
 * @author Li Yan
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {

    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_name(String name);

}
