package com.api.pricesCalculator.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PriceRequest", description = "Solicitud de precio")
public class PriceRequest {
    @NotNull(message = "brandId requerido!")
    @Min(1)
    @ApiModelProperty(value = "Identificación de la marca", example = "1")
    private Long brandId;

    @NotNull(message = "productId requerido!")
    @Min(1)
    @ApiModelProperty(value = "Identificación del producto", example = "35455")
    private Long productId;

    @NotNull(message = "applicationDate requerido!")
    @ApiModelProperty(value = "Fecha de solicitud", example = "2023-10-15T10:30:00")
    private LocalDateTime applicationDate;
}
