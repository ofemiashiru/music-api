package com.afrobeatslib.musicApi.config;

import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.repository.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class ArtistConfig {
    @Bean
    CommandLineRunner artistCommandLineRunner(ArtistRepository artistRepository){
        return args -> {
            Artist felaKuti = new Artist(
                    UUID.randomUUID(),
                    "Fela Kuti",
                    1
            );
            Artist burnaBoy = new Artist(
                    UUID.randomUUID(),
                    "Burna Boy",
                    2
            );
            Artist ayraStarr = new Artist(
                    UUID.randomUUID(),
                    "Ayra Starr",
                    4
            );

            artistRepository.saveAll(
                    List.of(felaKuti, burnaBoy, ayraStarr)
            );
        };
    }
}
