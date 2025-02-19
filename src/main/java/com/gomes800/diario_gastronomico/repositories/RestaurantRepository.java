package com.gomes800.diario_gastronomico.repositories;

import com.gomes800.diario_gastronomico.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
