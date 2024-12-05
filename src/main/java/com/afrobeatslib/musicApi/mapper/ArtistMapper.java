package com.afrobeatslib.musicApi.mapper;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.dto.ArtistInputDto;
import com.afrobeatslib.musicApi.model.Artist;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistMapper {

    public ArtistDto toDto(Artist artist){
        if(artist == null){
            return null;
        }

        ArtistDto dto = new ArtistDto();

        dto.setId(artist.getId());
        dto.setArtistName(artist.getArtistName());
        dto.setArtistImageUrl(artist.getArtistImageUrl());
        dto.setArtistGenres(artist.getArtistGenres());

        return dto;
    }

    public Artist toEntity(ArtistInputDto dto){

        if(dto == null){
            return null;
        }

        Artist entity = new Artist();

        entity.setId(UUID.fromString(dto.getId()));
        entity.setArtistName(dto.getArtistName());
        entity.setArtistImageUrl(dto.getArtistImageUrl());
        entity.setArtistGenres(dto.getArtistGenres());

        return entity;
    }
}
