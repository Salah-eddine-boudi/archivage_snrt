package com.example.audioplayer.controller;

import com.example.audioplayer.model.AudioFileInfo;
import com.example.audioplayer.service.AudioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/audio")
@CrossOrigin(origins = "*")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @Value("${audio.folder.path}")
    private String audioFolderPath;

    @GetMapping("/list")
    public ResponseEntity<List<AudioFileInfo>> getAllAudioFiles() {
        return ResponseEntity.ok(audioService.getAllAudioFiles());
    }

    @GetMapping("/info/{filename}")
    public ResponseEntity<AudioFileInfo> getAudioInfo(@PathVariable String filename) {
        AudioFileInfo info = audioService.getAudioInfoByFilename(filename);
        return (info != null) 
            ? ResponseEntity.ok(info) 
            : ResponseEntity.notFound().build();
    }

    @GetMapping("/info/id/{id}")
    public ResponseEntity<AudioFileInfo> getAudioInfoById(@PathVariable String id) {
        AudioFileInfo info = audioService.getAudioInfoById(id);
        return (info != null) 
            ? ResponseEntity.ok(info) 
            : ResponseEntity.notFound().build();
    }

    @GetMapping("/stream/{filename}")
    public ResponseEntity<UrlResource> streamAudio(@PathVariable String filename) {
        try {
            Path file = Paths.get(audioFolderPath).resolve(filename);
            UrlResource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }
            String type = filename.toLowerCase().endsWith(".wav")
                          ? "audio/wav"
                          : "audio/mpeg";
            return ResponseEntity.ok()
                                 .contentType(MediaType.parseMediaType(type))
                                 .header(HttpHeaders.CONTENT_DISPOSITION,
                                         "inline; filename=\"" + filename + "\"")
                                 .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<AudioFileInfo>> searchAudio(@RequestParam String query) {
        return ResponseEntity.ok(audioService.searchAudioFiles(query));
    }
}
