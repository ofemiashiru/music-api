package com.afrobeatslib.musicApi.dto;


import java.util.Set;
import java.util.UUID;

import com.afrobeatslib.musicApi.model.Genre;

public class ArtistDto {
    UUID id;
    String artistName;
    String artistImageUrl;
    Set<Genre> genres; 

    public ArtistDto(){

    }

    public ArtistDto(UUID id, String artistName, String artistImageUrl, Set<Genre> genres){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.genres = genres;
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID newID){
        this.id = newID;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public void setArtistName(String newArtistName){
        this.artistName = newArtistName;
    }

    public String getArtistImageUrl(){
        return this.artistImageUrl;
    }

    public void setArtistImageUrl(String newArtistImageUrl){
        this.artistImageUrl = newArtistImageUrl;
    }

    public Set<Genre> getArtistGenres() {
        return this.genres;
    }

    public void setArtistGenres(Set<Genre> newGenres) {
        this.genres = newGenres;
    }
}
