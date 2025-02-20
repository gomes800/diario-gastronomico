package com.gomes800.diario_gastronomico.services;

import com.gomes800.diario_gastronomico.domain.Restaurant;
import com.gomes800.diario_gastronomico.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id);
    }

    public Restaurant insert(Restaurant obj) {
        return repository.save(obj);
    }

    public void updateData(Restaurant entity, Restaurant obj) {
        entity.setName(obj.getName());
        entity.setAddress(obj.getAddress());
        entity.setPhone(obj.getPhone());
        entity.setSite(obj.getSite());
    }

    public Restaurant update(Long id, Restaurant obj) {
        try {
            Restaurant entity = repository.getReferenceById(id);
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
                throw new EntityNotFoundException("Restaurant not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database integrity violation: " + e.getMessage());
        }
    }
}
