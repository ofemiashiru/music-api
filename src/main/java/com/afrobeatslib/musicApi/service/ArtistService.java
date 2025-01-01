package com.afrobeatslib.musicApi.service;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.dto.ArtistInputDto;
import com.afrobeatslib.musicApi.mapper.ArtistMapper;
import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    private ArtistMapper artistMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDto> getArtists() {
        return artistRepository.findAll()
                .stream()
                .map(artist -> artistMapper.toDto(artist))
                .toList();
    }

    public ArtistDto getArtist(UUID id) {
        Artist artist = artistRepository.findById(id).orElse(null);

        if (artist == null) {
            throw new IllegalStateException("Does not exist");
        }

        return artistMapper.toDto(artist);
    }

    public ArtistDto addArtist(ArtistInputDto artistData) {
        Optional<Artist> existingArtist = artistRepository.findByArtistName(artistData.getArtistName());

        if (existingArtist.isPresent()) {
            throw new IllegalStateException("Artist already exists");
        }

        Artist newArtist = artistMapper.toEntity(artistData);
        Artist savedArtist = artistRepository.save(newArtist);

        return artistMapper.toDto(savedArtist);
    }

    public boolean deleteArtist(UUID id) {
        Artist artist = artistRepository.findById(id).orElse(null);

        if (artist == null) {
            throw new IllegalStateException("Does not exist");
        }

        artistRepository.deleteById(id);
        return true;

    }
}
