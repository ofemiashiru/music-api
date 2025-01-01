package com.afrobeatslib.musicApi.mapper;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.dto.ArtistInputDto;
import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.model.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afrobeatslib.musicApi.repository.GenreRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistMapper {

    @Autowired
    private GenreRepository genreRepository;

    public ArtistDto toDto(Artist artist){
        if(artist == null){
            return null;
        }

        ArtistDto dto = new ArtistDto();

        dto.setId(artist.getId());
        dto.setArtistName(artist.getArtistName());
        dto.setArtistImageUrl(artist.getArtistImageUrl());
        dto.setArtistGenres(artist.getGenres());

        return dto;
    }

    public Artist toEntity(ArtistInputDto dto){

        if(dto == null){
            return null;
        }

        Artist entity = new Artist();

        entity.setId(UUID.randomUUID());
        entity.setArtistName(dto.getArtistName());
        entity.setArtistImageUrl(dto.getArtistImageUrl());
        
        Set<Genre> genres = dto.getGenres().stream()
            .map(genreInput -> genreRepository.findById(genreInput.getId()).orElseThrow(
                () -> new EntityNotFoundException("Genre not found: " + genreInput.getId())
            )).collect(Collectors.toSet());

        entity.setGenres(genres);

        return entity;
    }
}
