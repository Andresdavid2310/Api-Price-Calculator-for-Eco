package com.api.pricesCalculator.integrationtest;

import com.api.pricesCalculator.controller.pricecontroller.PriceController;
import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PricesCalculatorApplicationTests {

	@Autowired
	private PriceController priceController;

	@ParameterizedTest
	@CsvFileSource(resources = "/prices.csv", numLinesToSkip = 1)
	void restIntegrationTest(ArgumentsAccessor argumentsAccessor) {
		LocalDateTime appDate = LocalDateTime.of(
				argumentsAccessor.getInteger(2), // Year
				argumentsAccessor.getInteger(3), // Month
				argumentsAccessor.getInteger(4), // Day
				argumentsAccessor.getInteger(5), // Hour
				0
		);

		PriceRequest priceRequest = PriceRequest.builder()
				.brandId(argumentsAccessor.getLong(0)) // Brand ID
				.productId(argumentsAccessor.getLong(1)) // Product ID
				.applicationDate(appDate).build();

		ResponseEntity<PriceResponse> priceResponse = priceController.getPrice(priceRequest);
		if (priceResponse.getStatusCode() == HttpStatus.OK) {
			PriceResponse price = priceResponse.getBody();
			if (price != null) {
				assertEquals(price.getBrandId(), argumentsAccessor.getLong(0)); // Brand ID
				assertEquals(price.getProductId(), argumentsAccessor.getLong(1)); // Product ID
				assertEquals(price.getApplicationDate(), appDate);
				assertEquals(price.getFinalPrice(), argumentsAccessor.getDouble(6)); // Final Price
				assertEquals(price.getRate(), argumentsAccessor.getLong(7)); // Price List
			}
		} else {
			System.err.println(priceResponse.getStatusCode());
		}
	}

}
