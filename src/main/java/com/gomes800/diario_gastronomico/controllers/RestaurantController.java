package com.gomes800.diario_gastronomico.controllers;


import com.gomes800.diario_gastronomico.domain.Restaurant;
import com.gomes800.diario_gastronomico.dto.PlaceResult;
import com.gomes800.diario_gastronomico.services.GooglePlacesService;
import com.gomes800.diario_gastronomico.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @Autowired
    private GooglePlacesService googlePlacesService;

    @GetMapping
    public List<Restaurant> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = service.findById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlaceResult>> searchRestaurants(@RequestParam String query) {
        List<PlaceResult> results = googlePlacesService.searchRestaurants(query);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/insert")
    public ResponseEntity<Restaurant> insertRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = service.insert(restaurant);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = service.findById(id);
        if (existingRestaurant.isPresent()) {
            Restaurant updateRestaurant = service.update(id, restaurant);
            return ResponseEntity.ok(updateRestaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        if (service.existById(id)) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
