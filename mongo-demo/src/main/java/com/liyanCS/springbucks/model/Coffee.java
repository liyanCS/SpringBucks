package com.liyanCS.springbucks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Li Yan
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {

    @Id
    private String id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
