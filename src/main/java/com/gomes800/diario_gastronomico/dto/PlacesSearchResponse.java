package com.gomes800.diario_gastronomico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlacesSearchResponse {
    private List<PlaceResult> results;
    private String status;
    @JsonProperty("next_page_token")
    private String nextPageToken;
}