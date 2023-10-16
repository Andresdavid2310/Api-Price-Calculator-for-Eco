package com.api.pricesCalculator.domain.dto.response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PriceResponse", description = "Respuesta de precios")
public class PriceResponse {
    private String code;
    private String message;

    @ApiModelProperty(value = "Identificación de la tafica a aplicar", example = "1")
    private Long rate;

    @ApiModelProperty(value = "Identificación de la marca", example = "1")
    private Long brandId;

    @ApiModelProperty(value = "Identificación del producto", example = "35455")
    private Long productId;

    @ApiModelProperty(value = "Fecha de solicitud", example = "2023-10-15T10:30:00")
    private LocalDateTime applicationDate;

    @ApiModelProperty(value = "Precio final", example = "99.99")
    private Double finalPrice;
}
