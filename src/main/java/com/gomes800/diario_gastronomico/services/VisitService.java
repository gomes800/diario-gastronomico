package com.gomes800.diario_gastronomico.services;

import com.gomes800.diario_gastronomico.domain.Restaurant;
import com.gomes800.diario_gastronomico.domain.User;
import com.gomes800.diario_gastronomico.domain.Visit;
import com.gomes800.diario_gastronomico.dto.VisitRequestDTO;
import com.gomes800.diario_gastronomico.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository repository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;


    public List<Visit> findAll() {
        return repository.findAll();
    }

    public Optional<Visit> findById(Long id) {
        return repository.findById(id);
    }

    public Visit insertVisitWithoutPhoto(VisitRequestDTO visitDto) {
        User user = userService.findById(visitDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Restaurant restaurant = restaurantService.findById(visitDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        Visit visit = new Visit();
        visit.setUser(user);
        visit.setRestaurant(restaurant);
        visit.setRating(visitDto.getRating());
        visit.setComment(visitDto.getComment());

        if (visitDto.getVisitDate() != null) {
            visit.setVisitDate(LocalDateTime.parse(visitDto.getVisitDate()));
        }

        return repository.save(visit);
    }

    public Visit addPhotoToVisit(Long visitId, MultipartFile file) throws IOException {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visita não encontrada"));

        if (file != null) {
            String imageUrl = firebaseStorageService.uploadImage(file);
            visit.getPhotos().add(imageUrl);
        }

        return repository.save(visit);
    }

    private void updateData(Visit entity, Visit obj) {
        entity.setRestaurant(obj.getRestaurant());
        entity.setRating(obj.getRating());
        entity.setComment(obj.getComment());
        entity.setVisitDate(obj.getVisitDate());
    }

    public Visit update(Long id, Visit obj) {
        try {
            Visit entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Visit not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database integrity violation: " + e.getMessage());
        }
    }

    public boolean existById(Long id) {
        return repository.existsById(id);
    }
}
