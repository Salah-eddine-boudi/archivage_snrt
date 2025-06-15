package com.example.audioplayer.repository;

import com.example.audioplayer.model.AudioFileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudioRepository
        extends JpaRepository<AudioFileInfo, String> {   // <- ID=String

    Optional<AudioFileInfo> findBySoundfileName(String name);

    @Query("SELECT a FROM AudioFileInfo a WHERE " +
           "LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(a.author) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(a.soundfileName) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<AudioFileInfo> searchByQuery(@Param("q") String q);
}
