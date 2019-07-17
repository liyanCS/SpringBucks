package com.liyanCS.springbucks.repository;

import com.liyanCS.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Li Yan
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
