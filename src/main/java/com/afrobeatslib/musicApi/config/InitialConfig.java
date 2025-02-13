package com.afrobeatslib.musicApi.config;

import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.repository.ArtistRepository;
import com.afrobeatslib.musicApi.repository.GenreRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class InitialConfig {
        @Bean
        CommandLineRunner artistCommandLineRunner(ArtistRepository artistRepository, GenreRepository genreRepository) {

                return args -> {
                        Genre afrobeat = new Genre(
                                        1,
                                        "Afrobeat");
                        Genre afrobeats = new Genre(
                                        2,
                                        "Afrobeats");
                        Genre afrofusion = new Genre(
                                        3,
                                        "Afrofusion");
                        Genre afropop = new Genre(
                                        4,
                                        "Afropop");
                        Genre amapiano = new Genre(
                                        5,
                                        "Amapiano");
                        Genre banku = new Genre(
                                        6,
                                        "Banku");
                        Genre fuji = new Genre(
                                        7,
                                        "Fuji");
                        Genre gospel = new Genre(
                                        8,
                                        "Gospel");
                        Genre highlife = new Genre(
                                        9,
                                        "Highlife");
                        Genre hiphop = new Genre(
                                        10,
                                        "Hip-hop");
                        genreRepository.saveAll(
                                        List.of(
                                                        afrobeat, afrobeats, afrofusion,
                                                        afropop, amapiano, banku, fuji,
                                                        gospel, highlife, hiphop));

                        Artist felaKuti = new Artist(
                                        UUID.randomUUID(),
                                        "Fela Kuti",
                                        "https://musicfileuploads.blob.core.windows.net/images/fela-kuti.webp");

                        Artist burnaBoy = new Artist(
                                        UUID.randomUUID(),
                                        "Burna Boy",
                                        "https://musicfileuploads.blob.core.windows.net/images/burna-boy.webp");

                        Artist ayraStarr = new Artist(
                                        UUID.randomUUID(),
                                        "Ayra Starr",
                                        "https://musicfileuploads.blob.core.windows.net/images/ayra-starr.webp");

                        felaKuti.addGenre(afrobeat);
                        burnaBoy.addGenre(afrobeats);
                        burnaBoy.addGenre(afrofusion);
                        ayraStarr.addGenre(afropop);
                        ayraStarr.addGenre(afrobeats);

                        artistRepository.saveAll(
                                        List.of(felaKuti, burnaBoy, ayraStarr));
                };
        }
}
