package com.api.pricesCalculator.domain.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
