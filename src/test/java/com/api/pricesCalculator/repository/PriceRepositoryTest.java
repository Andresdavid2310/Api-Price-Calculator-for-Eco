package com.api.pricesCalculator.repository;

import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.domain.model.entity.Brand;
import com.api.pricesCalculator.domain.model.entity.Price;
import com.api.pricesCalculator.domain.model.entity.PriceDetail;
import com.api.pricesCalculator.domain.model.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PriceRepositoryTest {

    @Mock
    private PriceRepository priceRepository;
    PriceRequest priceRequest = new PriceRequest();
    Product product = new Product();
    Brand brand = new Brand();
    List<Price> prices = new ArrayList<>();

    PriceResponse expectedResponse = new PriceResponse();
    Price price = new Price();
    @BeforeEach
    public void setUp() {
        priceRequest.setBrandId(1L);
        priceRequest.setProductId(35455L);
        priceRequest.setApplicationDate(LocalDateTime.parse("2020-06-15T00:50:00"));

        brand.setId(1L);
        brand.setName("Zara");

        product.setId(35455L);
        product.setName("Producto 2");

        PriceDetail priceDetail = new PriceDetail();
        priceDetail.setPrice(35.50);
        priceDetail.setPriceId(1L);
        priceDetail.setCurr("EUR");
        priceDetail.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        priceDetail.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        priceDetail.setPriority(1);

        price.setBrand(brand);
        price.setProduct(product);
        price.setPriceDetails(priceDetail);
        price.setId(1L);
        prices.add(price);

        expectedResponse.setBrandId(1L);
        expectedResponse.setRate(1L);
        expectedResponse.setProductId(35455L);
        expectedResponse.setApplicationDate(LocalDateTime.parse("2020-06-15T00:50:00"));
        expectedResponse.setFinalPrice(35.5);
    }
    @Test
    public void testFindByBrandIdAndProductId() {
        Mockito.when(priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getApplicationDate(), priceRequest.getApplicationDate()))
                .thenReturn(prices);
        List<Price> result = priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getApplicationDate(), priceRequest.getApplicationDate());

        Assertions.assertEquals(prices, result);
    }
}
