package com.afrobeatslib.musicApi.service;
import com.afrobeatslib.musicApi.mapper.ArtistMapper;
import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.model.Genre;
import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.repository.ArtistRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtistServiceTest {

    private ArtistMapper mockArtistMapper;
    private ArtistRepository mockArtistRepository;
    private ArtistService mockArtistService; 

    private List<Artist> expectedArtists;


    @BeforeEach
    void setUp(){
        mockArtistMapper = mock(ArtistMapper.class);
        mockArtistRepository = mock(ArtistRepository.class);
        mockArtistService = new ArtistService(mockArtistRepository, mockArtistMapper); 

        Genre afrobeat = new Genre(1, "Afrobeat");
        Genre afrobeats = new Genre(2, "Dancehall");
        Genre hiphop = new Genre(3, "Hip-Hop");

        Artist artistOne = new Artist(
                UUID.randomUUID(),
                "New Artist 1",
                "UrlOfArtistImage1"
        );
        artistOne.addGenre(hiphop);

        Artist artistTwo = new Artist(
                UUID.randomUUID(),
                "New Artist 2",
                "UrlOfArtistImage2"
        );
        artistTwo.addGenre(afrobeat);

        Artist artistThree = new Artist(
                UUID.randomUUID(),
                "New Artist 3",
                "UrlOfArtistImage3"
        );
        artistThree.addGenre(afrobeats);

        expectedArtists = new ArrayList<>();
        expectedArtists.add(artistOne);
        expectedArtists.add(artistTwo);
        expectedArtists.add(artistThree);
    }

    @DisplayName("Test that all artists are returned")
    @Test
    void testThatArtistsAreReturned(){

        when(mockArtistRepository.findAll()).thenReturn(expectedArtists);
        when(mockArtistMapper.toDto(any(Artist.class))).thenAnswer(invocation -> {
            Artist artist = invocation.getArgument(0);
            return new ArtistDto(
                artist.getId(),
                artist.getArtistName(),
                artist.getArtistImageUrl(),
                artist.getArtistGenres()
            );
        });

        List<ArtistDto> actual = mockArtistService.getArtists();
        
        assertNotNull(actual);
        assertEquals(expectedArtists.size(), actual.size());
        
        for(int i = 0; i < actual.size(); i++){
                assertEquals(expectedArtists.get(i).getArtistName(), actual.get(i).getArtistName());
        }

    }

}