package com.example.audioplayer.Repository;

import com.example.audioplayer.model.AudioFileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudioInfoRepository extends JpaRepository<AudioFileInfo, Long> {
    
    // Recherche par nom de fichier exact
    Optional<AudioFileInfo> findBySoundfileName(String soundfileName);
    
    // Recherche par titre (insensible à la casse)
    List<AudioFileInfo> findByTitleContainingIgnoreCase(String title);
    
    // Recherche par auteur (insensible à la casse)
    List<AudioFileInfo> findByAuthorContainingIgnoreCase(String author);
    
    // Recherche dans plusieurs champs
    List<AudioFileInfo> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrSoundfileNameContainingIgnoreCase(
            String title, String author, String soundfileName);
    
    // Recherche par durée
    List<AudioFileInfo> findByDureeMs(Long dureeMs);
    
    // Recherche par mots-clés
    List<AudioFileInfo> findByKeywordsContainingIgnoreCase(String keywords);
    
    // Recherche par album
    List<AudioFileInfo> findByAlbumDispNameContainingIgnoreCase(String albumName);
}