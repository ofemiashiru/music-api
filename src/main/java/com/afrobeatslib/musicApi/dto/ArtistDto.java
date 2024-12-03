package com.afrobeatslib.musicApi.dto;


import java.util.UUID;

public class ArtistDto {
    UUID id;
    String artistName;
    int artistGenreId;

    public ArtistDto(){

    }

    public ArtistDto(UUID id, String artistName, int artistGenreId){
        this.id = id;
        this.artistName = artistName;
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

    public int getArtistGenreId(){
        return this.artistGenreId;
    }

    public void setArtistGenreId(int newGenreId){
        this.artistGenreId = newGenreId;
    }
}
