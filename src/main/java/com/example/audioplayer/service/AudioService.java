package com.example.audioplayer.service;

import com.example.audioplayer.model.AudioFileInfo;

import java.util.List;

public interface AudioService {
    List<AudioFileInfo> getAllAudioFiles();
    AudioFileInfo getAudioInfoByFilename(String filename);
    AudioFileInfo getAudioInfoById(String id);
    List<AudioFileInfo> searchAudioFiles(String query);
}
