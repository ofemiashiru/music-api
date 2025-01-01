package com.afrobeatslib.musicApi.controller;

import com.afrobeatslib.musicApi.dto.GenreInputDto;
import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.service.GenreService;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GenreController {

    GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @QueryMapping
    List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @MutationMapping
    Genre addGenre(@Argument GenreInputDto genreData) {
        return genreService.addGenre(genreData);
    }

    @MutationMapping
    Boolean deleteGenre(@Argument Integer id) {
        return genreService.deleteGenre(id);
    }
}
