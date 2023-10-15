package com.api.pricesCalculator.controller;

import com.api.pricesCalculator.controller.pricecontroller.PriceController;
import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.exception.BadRequestException;
import com.api.pricesCalculator.interfaces.PriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceUseCase priceUseCase;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void getPrice_ValidRequest_ReturnsPriceResponse() {
        PriceRequest priceRequest = new PriceRequest(1L,35455L, LocalDateTime.parse("2020-06-15T00:50:00"));
        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setRate(1L);
        expectedResponse.setProductId(35455L);
        expectedResponse.setApplicationDate(LocalDateTime.parse("2020-06-15T00:50:00"));
        expectedResponse.setBrandId(1L);
        expectedResponse.setFinalPrice(35.50);
        when(priceUseCase.getPrice(priceRequest)).thenReturn(expectedResponse);

        ResponseEntity<PriceResponse> response = priceController.getPrice(priceRequest);

        verify(priceUseCase, times(1)).getPrice(priceRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        assertEquals(expectedResponse.getRate(), Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(expectedResponse.getBrandId(), Objects.requireNonNull(response.getBody().getBrandId()));
        assertEquals(expectedResponse.getProductId(), Objects.requireNonNull(response.getBody().getProductId()));
        assertEquals(expectedResponse.getFinalPrice(), Objects.requireNonNull(response.getBody().getFinalPrice()));
    }

    @Test
    public void getPrice_BadRequestException_ReturnsBadRequestResponse() {
        PriceRequest priceRequest = new PriceRequest();
        when(priceUseCase.getPrice(priceRequest)).thenThrow(new BadRequestException("Bad Request"));

        ResponseEntity<PriceResponse> response = priceController.getPrice(priceRequest);

        verify(priceUseCase, times(1)).getPrice(priceRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPriceWithNoData() {
        PriceRequest priceRequest = new PriceRequest();
        given(priceUseCase.getPrice(priceRequest)).willReturn(null);
        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(priceRequest);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPriceWithInternalServerError() {
        PriceRequest priceRequest = new PriceRequest();
        given(priceUseCase.getPrice(priceRequest)).willThrow(new RuntimeException("Internal server error"));
        ResponseEntity<PriceResponse> responseEntity = priceController.getPrice(priceRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
