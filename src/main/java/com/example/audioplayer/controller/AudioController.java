package com.example.audioplayer.controller;

import com.example.audioplayer.model.AudioFileInfo;
import com.example.audioplayer.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/audio")
@CrossOrigin(origins = "*")
public class AudioController {

    @Autowired
    private AudioService audioService;

    // Endpoint pour récupérer toutes les informations audio
    @GetMapping("/list")
    public ResponseEntity<List<AudioFileInfo>> getAllAudioFiles() {
        try {
            List<AudioFileInfo> audioFiles = audioService.getAllAudioFiles();
            return ResponseEntity.ok(audioFiles);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint pour récupérer les informations d'un fichier spécifique par nom
    @GetMapping("/info/{filename}")
    public ResponseEntity<AudioFileInfo> getAudioInfo(@PathVariable String filename) {
        try {
            AudioFileInfo audioInfo = audioService.getAudioInfoByFilename(filename);
            if (audioInfo != null) {
                return ResponseEntity.ok(audioInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint pour récupérer les informations d'un fichier par ID
    @GetMapping("/info/id/{id}")
    public ResponseEntity<AudioFileInfo> getAudioInfoById(@PathVariable Long id) {
        try {
            AudioFileInfo audioInfo = audioService.getAudioInfoById(id);
            if (audioInfo != null) {
                return ResponseEntity.ok(audioInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint pour servir les fichiers audio
    @GetMapping("/stream/{filename}")
    public ResponseEntity<Resource> streamAudio(@PathVariable String filename) {
        try {
            // Récupérer le chemin du fichier depuis la base de données
            AudioFileInfo audioInfo = audioService.getAudioInfoByFilename(filename);
            
            if (audioInfo == null) {
                return ResponseEntity.notFound().build();
            }

            Path filePath = Paths.get("D:/stage 20242025/lake/mp3/" + filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = "audio/mpeg"; // Par défaut pour MP3
                
                // Déterminer le type de contenu selon l'extension
                if (filename.toLowerCase().endsWith(".wav")) {
                    contentType = "audio/wav";
                } else if (filename.toLowerCase().endsWith(".ogg")) {
                    contentType = "audio/ogg";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint pour rechercher des fichiers audio
    @GetMapping("/search")
    public ResponseEntity<List<AudioFileInfo>> searchAudio(@RequestParam String query) {
        try {
            List<AudioFileInfo> results = audioService.searchAudioFiles(query);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}