package com.gomes800.diario_gastronomico.client;

import com.gomes800.diario_gastronomico.dto.PlaceDetailsResponse;
import com.gomes800.diario_gastronomico.dto.PlacesSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googlePlacesClient", url = "${google.places.base-url}")
public interface GooglePlacesClient {

    @GetMapping("/textsearch/json")
    PlacesSearchResponse searchPlaces(
            @RequestParam("query") String query,
            @RequestParam("type") String type,
            @RequestParam("key") String apiKey
    );

    @GetMapping("/nearbysearch/json")
    PlacesSearchResponse searchNearbyPlaces(
            @RequestParam("location") String location,
            @RequestParam("radius") Integer radius,
            @RequestParam("type") String type,
            @RequestParam("key") String apiKey
    );

    @GetMapping("/details/json")
    PlaceDetailsResponse getPlaceDetails(
            @RequestParam("place_id") String placeId,
            @RequestParam("fields") String fields,
            @RequestParam("key") String apiKey
    );
}