package com.gomes800.diario_gastronomico.services;

import com.gomes800.diario_gastronomico.domain.User;
import com.gomes800.diario_gastronomico.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByID(Long id) {
        return repository.findById(id);
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
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
                throw new EntityNotFoundException("User not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database integrity violation: " + e.getMessage());
        }
    }

    public boolean existById(Long id) {
        return repository.existsById(id);
    }

}
