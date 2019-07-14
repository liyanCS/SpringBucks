package com.liyan.spring.data.mybatisdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * 咖啡实体， MENU菜单
 *
 * @author Li Yan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    private Long id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;

}