package com.gomes800.diario_gastronomico.controllers;

import com.gomes800.diario_gastronomico.domain.Visit;
import com.gomes800.diario_gastronomico.services.FirebaseStorageService;
import com.gomes800.diario_gastronomico.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService service;

    private FirebaseStorageService firebaseStorageService;

    @GetMapping
    public List<Visit> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> findById(@PathVariable Long id) {
        Optional<Visit> visit = service.findByID(id);
        return visit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public ResponseEntity<Visit> insertVisit(@RequestBody Visit visit) {
        Visit newVisit = service.insert(visit);
        return new ResponseEntity<>(newVisit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        Optional<Visit> existingVisit = service.findByID(id);
        if (existingVisit.isPresent()) {
            Visit updateVisit = service.update(id, visit);
            return ResponseEntity.ok(updateVisit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        if (service.existById(id)) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload-photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = firebaseStorageService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer upload da imagem.");
        }
    }

}
