package com.gomes800.diario_gastronomico.services;

import com.gomes800.diario_gastronomico.client.GooglePlacesClient;
import com.gomes800.diario_gastronomico.dto.PlaceResult;
import com.gomes800.diario_gastronomico.dto.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class GooglePlacesService {

    @Autowired
    private GooglePlacesClient googlePlacesClient;

    @Value("${google.places.api-key}")
    private String apiKey;

    public List<PlaceResult> searchRestaurants(String query) {
        PlacesSearchResponse response = googlePlacesClient.searchPlaces(
                query, "restaurant", apiKey);
        return response.getResults();
    }
}
