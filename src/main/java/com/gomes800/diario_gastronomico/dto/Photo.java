package com.gomes800.diario_gastronomico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Photo {
    @JsonProperty("photo_reference")
    private String photoReference;
    private Integer height;
    private Integer width;
}
