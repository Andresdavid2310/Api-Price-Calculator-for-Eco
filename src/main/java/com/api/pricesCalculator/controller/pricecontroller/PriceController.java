package com.api.pricesCalculator.controller.pricecontroller;

import com.api.pricesCalculator.domain.dto.request.PriceRequest;
import com.api.pricesCalculator.domain.dto.response.PriceResponse;
import com.api.pricesCalculator.exception.BadRequestException;
import com.api.pricesCalculator.exception.ResourceNotFoundException;
import com.api.pricesCalculator.interfaces.PriceUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Price", description = "Obtener informacion de los precios")
public class PriceController {

    private final PriceUseCase priceUseCase;

    @Autowired
    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @PostMapping(value = "/price")
    @ApiOperation("Obtener precios según brandId, productId y applicationDate")
    public ResponseEntity<PriceResponse> getPrice(@RequestBody @Valid PriceRequest priceRequest) {
        try {
            PriceResponse priceResponse = priceUseCase.getPrice(priceRequest);
            if (priceResponse != null){
                return ResponseEntity.ok(priceResponse);
            }
            else {
                PriceResponse newpriceResponse = new PriceResponse();
                newpriceResponse.setCode(HttpStatus.NO_CONTENT.toString());
                newpriceResponse.setMessage("Prices not found");
                return new ResponseEntity<>(newpriceResponse, HttpStatus.NOT_FOUND);
            }
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
