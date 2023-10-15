package com.api.pricesCalculator.domain.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "PRICE")
public class Price {
    @Id
    @Column(name = "PRICE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "PRICE_LIST",nullable = false)
    private PriceDetail priceDetails;

    /*@Id
    @Column(name = "PRICE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    private Long priceList;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String curr;*/
}
