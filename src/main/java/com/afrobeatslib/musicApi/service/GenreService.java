package com.afrobeatslib.musicApi.service;

import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }
}
