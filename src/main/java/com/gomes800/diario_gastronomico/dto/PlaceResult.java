package com.gomes800.diario_gastronomico.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaceResult {
    @JsonProperty("place_id")
    private String placeId;
    private String name;
    private Geometry geometry;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("formatted_phone_number")
    private String formattedPhoneNumber;
    private String website;
    @JsonProperty("photos")
    private List<Photo> photos;
    private Double rating;
}
