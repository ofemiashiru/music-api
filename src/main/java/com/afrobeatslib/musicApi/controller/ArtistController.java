package com.afrobeatslib.musicApi.controller;

import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.service.ArtistService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @QueryMapping
    public List<ArtistDto> getArtists(){
        return artistService.getArtists();
    }
}
