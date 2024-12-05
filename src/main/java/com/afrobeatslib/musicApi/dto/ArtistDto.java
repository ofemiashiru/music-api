package com.afrobeatslib.musicApi.dto;


import java.util.UUID;

public class ArtistDto {
    UUID id;
    String artistName;
    String artistImageUrl;
    int artistGenreId;

    public ArtistDto(){

    }

    public ArtistDto(UUID id, String artistName, String artistImageUrl, int artistGenreId){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.artistGenreId = artistGenreId;
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

    public int getArtistGenreId(){
        return this.artistGenreId;
    }

    public void setArtistGenreId(int newGenreId){
        this.artistGenreId = newGenreId;
    }
}
