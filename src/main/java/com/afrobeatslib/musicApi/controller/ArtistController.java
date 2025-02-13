package com.afrobeatslib.musicApi.controller;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.dto.ArtistInputDto;
import com.afrobeatslib.musicApi.service.ArtistService;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @QueryMapping
    public List<ArtistDto> getArtists() {
        return artistService.getArtists();
    }

    @QueryMapping
    public ArtistDto getArtist(@Argument UUID id) {
        return artistService.getArtist(id);
    }

    @MutationMapping
    public boolean deleteArtist(@Argument UUID id) {
        return artistService.deleteArtist(id);
    }

    @MutationMapping
    public ArtistDto addArtist(@Argument ArtistInputDto artistData) {
        return artistService.addArtist(artistData);
    }

}
