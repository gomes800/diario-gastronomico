package com.gomes800.diario_gastronomico.repositories;

import com.gomes800.diario_gastronomico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
