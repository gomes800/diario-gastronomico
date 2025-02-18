package com.gomes800.diario_gastronomico.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long id;
    private User user;
    private String name;
    private String address;
    private byte rating;
    private String comment;
    private LocalDateTime visitDate;
    private List<Photo> photos;
}
