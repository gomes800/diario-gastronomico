package com.gomes800.diario_gastronomico.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/diario-gastronomico-firebase-adminsdk-fbsvc-3f3e079a4c.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("diario-gastronomico.appspot.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
