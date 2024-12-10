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
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

        UUID fixedIdUuid = UUID.fromString("9137349e-394b-452e-8485-003511958147");

        Artist artistOne = new Artist(
                fixedIdUuid,
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

    @DisplayName("Test that I can get an artist back by id successfully")
    @Test
    void testThatAnArtistIsReturnedById(){

        UUID artistId = UUID.fromString("9137349e-394b-452e-8485-003511958147");
        Optional<Artist> expectedArtist = Optional.ofNullable(expectedArtists.get(0));

        when(mockArtistRepository.findById(artistId)).thenReturn(expectedArtist);
        when(mockArtistMapper.toDto(any(Artist.class))).thenReturn(
            new ArtistDto(
                expectedArtist.get().getId(),
                expectedArtist.get().getArtistName(),
                expectedArtist.get().getArtistImageUrl(),
                expectedArtist.get().getArtistGenres()
            )
        );
        
        ArtistDto actualArtist = mockArtistService.getArtist(artistId);
        assertNotNull(actualArtist);
        assertEquals(expectedArtist.get().getId(), actualArtist.getId());
        
    }

    @DisplayName("Test that artist is deleted successfully")
    @Test
    void testThatASingleArtistIsDeleted() throws IllegalStateException{
        UUID artistUuid = UUID.fromString("9137349e-394b-452e-8485-003511958147");

        when(mockArtistRepository.findById(artistUuid))
        .thenReturn(
            expectedArtists.stream().filter(artist -> artist.getId().equals(artistUuid)).findFirst()
        );

        boolean artistIsDeleted = mockArtistService.deleteArtist(artistUuid);

        verify(mockArtistRepository).deleteById(artistUuid);

        assertTrue(artistIsDeleted);
        
    }
}