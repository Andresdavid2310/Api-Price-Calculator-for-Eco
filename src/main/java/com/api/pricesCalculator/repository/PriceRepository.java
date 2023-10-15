package com.api.pricesCalculator.repository;

import com.api.pricesCalculator.domain.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
   List<Price> findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(
           Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDateEnd);
}
