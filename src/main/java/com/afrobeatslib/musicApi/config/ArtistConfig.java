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
                    "https://afrobeatbucket.s3.eu-west-1.amazonaws.com/artist_images/fela-kuti.webp",
                    1
            );
            Artist burnaBoy = new Artist(
                    UUID.randomUUID(),
                    "Burna Boy",
                    "https://afrobeatbucket.s3.eu-west-1.amazonaws.com/artist_images/burna-boy.webp",
                    2
            );
            Artist ayraStarr = new Artist(
                    UUID.randomUUID(),
                    "Ayra Starr",
                    "https://afrobeatbucket.s3.eu-west-1.amazonaws.com/artist_images/ayra-starr.webp",
                    4
            );

            artistRepository.saveAll(
                    List.of(felaKuti, burnaBoy, ayraStarr)
            );
        };
    }
}
