package com.api.pricesCalculator.domain.usecase.price;


import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.domain.model.entity.Price;
import com.api.pricesCalculator.interfaces.PriceUseCase;
import com.api.pricesCalculator.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceUseCaseImpl implements PriceUseCase {


    private final PriceRepository priceRepository;
    @Override
    public PriceResponse getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndPriceDetailsStartDateLessThanEqualAndPriceDetailsEndDateGreaterThanEqualOrderByPriceDetailsPriorityAsc(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getApplicationDate(), priceRequest.getApplicationDate());
        PriceResponse priceResponse = null;
        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(price1 -> price1.getPriceDetails().getPriority())).get();
            priceResponse = makePriceResponse(priceRequest, price);
        }
        return priceResponse;
    }

    private PriceResponse makePriceResponse(PriceRequest priceRequest, Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setApplicationDate(priceRequest.getApplicationDate());
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setFinalPrice(price.getPriceDetails().getPrice());
        priceResponse.setRate(price.getPriceDetails().getPriceId());
        priceResponse.setProductId(price.getProduct().getId());
        return priceResponse;
    }

}
