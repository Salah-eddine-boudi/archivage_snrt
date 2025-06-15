package com.example.audioplayer.service;

import com.example.audioplayer.model.AudioFileInfo;
import com.example.audioplayer.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class AudioServiceImpl implements AudioService {

    private final AudioRepository repo;

    @Value("${audio.folder.path}")
    private String audioFolderPath;

    public AudioServiceImpl(AudioRepository repo) {
        this.repo = repo;
    }

    /**
     * Retourne un unique AudioFileInfo par fichier (base name),
     * avec priorité aux métadonnées existantes (ex: .wav),
     * sinon création minimale et lecture du premier fichier trouvé.
     */
    @Override
    public List<AudioFileInfo> getAllAudioFiles() {
        File folder = new File(audioFolderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = folder.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".mp3") || lower.endsWith(".wav") || lower.endsWith(".ogg");
        });
        if (files == null) {
            return Collections.emptyList();
        }
        // Trier pour donner priorité à .wav (metadata en BDD) avant .mp3
        Arrays.sort(files, Comparator.comparing(File::getName, (a, b) -> {
            boolean aw = a.toLowerCase().endsWith(".wav");
            boolean bw = b.toLowerCase().endsWith(".wav");
            if (aw && !bw) return -1;
            if (!aw && bw) return 1;
            return a.compareToIgnoreCase(b);
        }));

        Map<String, AudioFileInfo> map = new LinkedHashMap<>();
        for (File file : files) {
            String filename = file.getName();
            String baseName = filename.substring(0, filename.lastIndexOf('.'));
            // si déjà ajouté, on skip
            if (map.containsKey(baseName)) continue;
            // récupère métadonnée exact si existante
            AudioFileInfo info = repo.findBySoundfileName(filename)
                .orElseGet(() -> {
                    AudioFileInfo tmp = new AudioFileInfo();
                    tmp.setSoundfileName(filename);
                    tmp.setTitle(baseName);
                    tmp.setAuthor("Inconnu");
                    return tmp;
                });
            map.put(baseName, info);
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public AudioFileInfo getAudioInfoByFilename(String filename) {
        return repo.findBySoundfileName(filename).orElse(null);
    }

    @Override
    public AudioFileInfo getAudioInfoById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<AudioFileInfo> searchAudioFiles(String query) {
        return repo.searchByQuery(query);
    }
}
