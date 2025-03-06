package com.gomes800.diario_gastronomico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_visit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private byte rating;
    private String comment;
    private LocalDateTime visitDate;

    @PrePersist
    private void prePersist() {
        visitDate = LocalDateTime.now();
    }

    @ElementCollection
    @CollectionTable(name = "visit_photos", joinColumns = @JoinColumn(name = "visit_id"))
    @Column(name = "photo_url")
    private List<String> photos = new ArrayList<>();
}
