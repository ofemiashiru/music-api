package com.afrobeatslib.musicApi.controller;

import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.service.GenreService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GenreController {

    GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @QueryMapping
    List<Genre> getGenres(){
        return genreService.getGenres();
    }
}
