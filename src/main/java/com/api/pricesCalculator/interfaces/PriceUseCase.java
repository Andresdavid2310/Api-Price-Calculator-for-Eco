package com.api.pricesCalculator.interfaces;


import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;

public interface PriceUseCase {
    PriceResponse getPrice(PriceRequest priceRequest);
}
