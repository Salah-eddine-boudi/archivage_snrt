package com.example.audioplayer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "java_test") // Nom de votre table dans la BDD
public class AudioFileInfo {
    
    @Id
    @Column(name = "title_id")
    private Long titleId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "soundfile_name")
    private String soundfileName;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "duree_ms")
    private Long dureeMs;
    
    @Column(name = "duree")
    private String duree;
    
    @Column(name = "is_online")
    private Integer isOnline;
    
    @Column(name = "record_date")
    private LocalDateTime recordDate;
    
    @Column(name = "last_modif_time")
    private LocalDateTime lastModifTime;
    
    @Column(name = "interpret")
    private String interpret;
    
    @Column(name = "keywords")
    private String keywords;
    
    @Column(name = "company_disp_name")
    private String companyDispName;
    
    @Column(name = "album_disp_name")
    private String albumDispName;
    
    @Column(name = "commentaire1")
    private String commentaire1;
    
    @Column(name = "commentaire2")
    private String commentaire2;

    // Constructeurs
    public AudioFileInfo() {}

    public AudioFileInfo(String title, String soundfileName, String author) {
        this.title = title;
        this.soundfileName = soundfileName;
        this.author = author;
    }

    // Getters et Setters
    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSoundfileName() {
        return soundfileName;
    }

    public void setSoundfileName(String soundfileName) {
        this.soundfileName = soundfileName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getDureeMs() {
        return dureeMs;
    }

    public void setDureeMs(Long dureeMs) {
        this.dureeMs = dureeMs;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public LocalDateTime getLastModifTime() {
        return lastModifTime;
    }

    public void setLastModifTime(LocalDateTime lastModifTime) {
        this.lastModifTime = lastModifTime;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCompanyDispName() {
        return companyDispName;
    }

    public void setCompanyDispName(String companyDispName) {
        this.companyDispName = companyDispName;
    }

    public String getAlbumDispName() {
        return albumDispName;
    }

    public void setAlbumDispName(String albumDispName) {
        this.albumDispName = albumDispName;
    }

    public String getCommentaire1() {
        return commentaire1;
    }

    public void setCommentaire1(String commentaire1) {
        this.commentaire1 = commentaire1;
    }

    public String getCommentaire2() {
        return commentaire2;
    }

    public void setCommentaire2(String commentaire2) {
        this.commentaire2 = commentaire2;
    }

    @Override
    public String toString() {
        return "AudioFileInfo{" +
                "titleId=" + titleId +
                ", title='" + title + '\'' +
                ", soundfileName='" + soundfileName + '\'' +
                ", author='" + author + '\'' +
                ", duree='" + duree + '\'' +
                '}';
    }
}