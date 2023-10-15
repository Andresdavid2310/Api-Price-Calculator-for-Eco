package com.api.pricesCalculator.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse {
    private String code;
    private String message;
    private Long rate;
    private Long brandId;
    private Long productId;
    private LocalDateTime applicationDate;
    private Double finalPrice;
}
