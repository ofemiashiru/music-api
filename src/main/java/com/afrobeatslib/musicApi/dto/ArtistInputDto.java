package com.afrobeatslib.musicApi.dto;

import java.util.Set;

import com.afrobeatslib.musicApi.model.Genre;

public class ArtistInputDto {
    String id;
    String artistName;
    String artistImageUrl;
    Set<Genre> genres; 

    public ArtistInputDto(){

    }

    public ArtistInputDto(String id, String artistName, String artistImageUrl, Set<Genre> genres){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.genres = genres;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String newID){
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
