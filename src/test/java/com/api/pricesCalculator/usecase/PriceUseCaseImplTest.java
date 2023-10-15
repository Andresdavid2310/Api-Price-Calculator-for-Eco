package com.api.pricesCalculator.usecase;

import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.domain.model.entity.Brand;
import com.api.pricesCalculator.domain.model.entity.Price;
import com.api.pricesCalculator.domain.model.entity.PriceDetail;
import com.api.pricesCalculator.domain.model.entity.Product;
import com.api.pricesCalculator.domain.usecase.price.PriceUseCaseImpl;
import com.api.pricesCalculator.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceUseCaseImplTest {

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
        when(priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(1L, 35455L, priceRequest.getApplicationDate(), priceRequest.getApplicationDate())).thenReturn(prices);
    }

    @Test
    public void testGetPriceWithValidData() {
        PriceUseCaseImpl priceUseCase = new PriceUseCaseImpl(priceRepository);
        PriceResponse priceResponse = priceUseCase.getPrice(priceRequest);
        assertEquals(expectedResponse.getBrandId(), Objects.requireNonNull(priceResponse.getBrandId()));
        assertEquals(expectedResponse.getRate(), Objects.requireNonNull(priceResponse.getRate()));
        assertEquals(expectedResponse.getApplicationDate(), Objects.requireNonNull(priceResponse.getApplicationDate()));
        assertEquals(expectedResponse.getFinalPrice(), Objects.requireNonNull(priceResponse.getFinalPrice()));
        assertEquals(expectedResponse.getProductId(), Objects.requireNonNull(priceResponse.getProductId()));
    }

    @Test
    public void testGetPriceWithNoData() {
        when(priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(1L, 35455L, priceRequest.getApplicationDate(), priceRequest.getApplicationDate())).thenReturn(new ArrayList<>());
        PriceUseCaseImpl priceUseCase = new PriceUseCaseImpl(priceRepository);
        PriceResponse priceResponse = priceUseCase.getPrice(priceRequest);
        assertNull(priceResponse);
    }
}
