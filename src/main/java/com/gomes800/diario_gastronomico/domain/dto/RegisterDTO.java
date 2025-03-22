package com.gomes800.diario_gastronomico.domain.dto;

import com.gomes800.diario_gastronomico.domain.UserRole;

public record RegisterDTO(String login, String password, String role) {
}
