package com.gomes800.diario_gastronomico.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private Long id;
    private Restaurant restaurant;
    private String url;
    private LocalDateTime uploadDate;
}
