package com.afrobeatslib.musicApi.service;
import com.afrobeatslib.musicApi.mapper.ArtistMapper;
import com.afrobeatslib.musicApi.model.Artist;
import com.afrobeatslib.musicApi.dto.ArtistDto;
import com.afrobeatslib.musicApi.repository.ArtistRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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

        expectedArtists = new ArrayList<>();
        expectedArtists.add(
                new Artist(
                        UUID.randomUUID(),
                        "New Artist 1",
                        "UrlOfArtistImage1",
                        2
                )
        );
        expectedArtists.add(
                new Artist(
                        UUID.randomUUID(),
                        "New Artist 2",
                        "UrlOfArtistImage2",
                        4
                )
        );
        expectedArtists.add(
                new Artist(
                        UUID.randomUUID(),
                        "New Artist 3",
                        "UrlOfArtistImage3",
                        5
                )
        );
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
                artist.getArtistGenreId()
            );
        });

        List<ArtistDto> actual = mockArtistService.getArtists();

        System.out.println(actual);
        
        assertNotNull(actual);
        assertEquals(expectedArtists.size(), actual.size());
        
        for(int i = 0; i < actual.size(); i++){
                assertEquals(expectedArtists.get(i).getArtistName(), actual.get(i).getArtistName());
        }

    }

}