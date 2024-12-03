package com.afrobeatslib.musicApi.service;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.mapper.ArtistMapper;
import com.afrobeatslib.musicApi.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    private ArtistMapper artistMapper;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper){
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDto> getArtists(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> artistMapper.toDto(artist))
                .toList();
    }
}
