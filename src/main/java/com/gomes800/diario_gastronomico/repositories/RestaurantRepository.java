package com.gomes800.diario_gastronomico.repositories;

import com.gomes800.diario_gastronomico.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
