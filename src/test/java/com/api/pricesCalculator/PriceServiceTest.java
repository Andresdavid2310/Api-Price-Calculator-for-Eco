package com.api.pricesCalculator;

import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.domain.model.entity.Brand;
import com.api.pricesCalculator.domain.model.entity.Price;
import com.api.pricesCalculator.domain.model.entity.PriceDetail;
import com.api.pricesCalculator.domain.model.entity.Product;
import com.api.pricesCalculator.repository.PriceRepository;
import com.api.pricesCalculator.domain.usecase.price.PriceUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceUseCaseImpl priceService;

    private List<Price> prices = new ArrayList<Price>();
    private LocalDateTime appDate = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("ZARA");
        PriceDetail priceDetail = new PriceDetail();
        Product product = new Product();
        for (int i = 0; i < 2; i++) {
            int valor = i+1;
            Price price = new Price();
            price.setId(Long.valueOf(valor));
            priceDetail.setPriceId((long) valor);
            priceDetail.setPrice((double) (20*valor));
            priceDetail.setCurr("EUR");
            priceDetail.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            priceDetail.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
            priceDetail.setPriority(0);
            price.setPriceDetails(priceDetail);
            product.setId(35455L);
            product.setName("Producto 1");
            price.setProduct(product);
            price.setBrand(brand);
            prices.add(price);
            System.out.println(price);
        }
    }

    @Test
    public void givenPriceRequest_whenGettingPrice_thenReturnPriceResponse() {
        given(priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(1L, 35455L, appDate,appDate)).willReturn(prices);

        PriceRequest priceRequest = PriceRequest.builder().applicationDate(appDate).brandId(1L).productId(35455L).build();
        PriceResponse priceResponse = priceService.getPrice(priceRequest);
        
        assertEquals(1L, priceResponse.getBrandId());
        assertEquals(2L, priceResponse.getRate());
        assertEquals(appDate, priceResponse.getApplicationDate());
        assertEquals(40L, priceResponse.getFinalPrice());
        assertEquals(35455L, priceResponse.getProductId());
    }

}