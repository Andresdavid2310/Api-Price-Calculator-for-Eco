package com.api.pricesCalculator.domain.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {
    @NotNull(message = "brandId requerido!")
    @Min(1)
    private Long brandId;

    @NotNull(message = "productId requerido!")
    @Min(1)
    private Long productId;

    @NotNull(message = "applicationDate requerido!")
    private LocalDateTime applicationDate;
}
