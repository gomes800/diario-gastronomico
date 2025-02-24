package com.gomes800.diario_gastronomico.services;

import com.gomes800.diario_gastronomico.domain.Visit;
import com.gomes800.diario_gastronomico.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository repository;

    public List<Visit> findAll() {
        return repository.findAll();
    }

    public Optional<Visit> findByID(Long id) {
        return repository.findById(id);
    }

    public Visit insert(Visit obj) {
        return repository.save(obj);
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
