package com.gomes800.diario_gastronomico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitRequestDTO {
    private Long userId;
    private Long restaurantId;
    private Byte rating;
    private String comment;
    private String visitDate;
}
