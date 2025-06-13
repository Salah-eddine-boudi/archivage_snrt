package com.example.audioplayer.service;

import com.example.audioplayer.model.AudioFileInfo;
import com.example.audioplayer.Repository.AudioInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioService {

    @Autowired
    private AudioInfoRepository audioInfoRepository;

    public List<AudioFileInfo> getAllAudioFiles() {
        return audioInfoRepository.findAll();
    }

    public AudioFileInfo getAudioInfoById(Long id) {
        Optional<AudioFileInfo> audioInfo = audioInfoRepository.findById(id);
        return audioInfo.orElse(null);
    }

    public AudioFileInfo getAudioInfoByFilename(String filename) {
        // Recherche par nom de fichier dans la colonne soundfile_name
        Optional<AudioFileInfo> audioInfo = audioInfoRepository.findBySoundfileName(filename);
        return audioInfo.orElse(null);
    }

    public List<AudioFileInfo> searchAudioFiles(String query) {
        // Recherche dans le titre, l'auteur ou le nom du fichier
        return audioInfoRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrSoundfileNameContainingIgnoreCase(
                query, query, query);
    }

    public AudioFileInfo saveAudioInfo(AudioFileInfo audioInfo) {
        return audioInfoRepository.save(audioInfo);
    }

    public void deleteAudioInfo(Long id) {
        audioInfoRepository.deleteById(id);
    }

    public List<AudioFileInfo> getAudioFilesByAuthor(String author) {
        return audioInfoRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<AudioFileInfo> getAudioFilesByTitle(String title) {
        return audioInfoRepository.findByTitleContainingIgnoreCase(title);
    }
}