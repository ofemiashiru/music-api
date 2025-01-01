package com.afrobeatslib.musicApi.service;

import com.afrobeatslib.musicApi.dto.GenreInputDto;
import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(GenreInputDto genreData) {
        Optional<Genre> existingGenre = genreRepository.findByGenreName(genreData.getGenreName());

        if (existingGenre.isPresent()) {
            throw new IllegalStateException("Genre already exists");
        }

        long totalGenres = genreRepository.count();
        if (totalGenres > Integer.MAX_VALUE) {
            // Handle the overflow condition (e.g., throw an exception)
            throw new RuntimeException("Total genres exceeded maximum value for Integer.");
        }

        Integer newId = (int) totalGenres + 1;

        Genre newGenre = new Genre();
        newGenre.setId(newId);
        newGenre.setGenreName(genreData.getGenreName());

        return genreRepository.save(newGenre);
    }

    public Boolean deleteGenre(Integer id) {

        Genre genre = genreRepository.findById(id).orElse(null);

        if (genre == null) {
            throw new IllegalStateException("Does not exist");
        }

        genreRepository.deleteById(id);
        return true;
    }
}
