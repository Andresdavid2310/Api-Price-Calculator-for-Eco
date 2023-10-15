package com.api.pricesCalculator.domain.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @Column(name = "BRAND_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
