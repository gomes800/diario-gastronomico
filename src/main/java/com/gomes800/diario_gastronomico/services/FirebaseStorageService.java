package com.gomes800.diario_gastronomico.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    @Value("${firebase.bucket-name:diario-gastronomico.appspot.com}")
    private String bucketName;

    @Value("${firebase.credentials-file:diario-gastronomico-firebase-adminsdk-fbsvc-0434ea0478.json}")
    private String credentialsFile;

    private Storage storage;

    @PostConstruct
    public void initialize() throws IOException {
        try {

            InputStream serviceAccount = new ClassPathResource(credentialsFile).getInputStream();

            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();

            System.out.println("Firebase Storage inicializado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar Firebase Storage: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (storage == null) {
            throw new IllegalStateException("Firebase Storage não foi inicializado corretamente");
        }

        try {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            Bucket bucket = storage.get(bucketName);
            if (bucket == null) {
                throw new IllegalStateException("Bucket '" + bucketName + "' não encontrado");
            }

            Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

            return blob.getMediaLink();
        } catch (Exception e) {
            System.err.println("Erro ao fazer upload da imagem: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}