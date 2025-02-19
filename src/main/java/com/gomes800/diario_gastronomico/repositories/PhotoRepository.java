package com.gomes800.diario_gastronomico.repositories;

import com.gomes800.diario_gastronomico.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
