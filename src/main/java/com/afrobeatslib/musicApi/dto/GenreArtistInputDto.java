package com.afrobeatslib.musicApi.dto;

public class GenreArtistInputDto {
    
    private int id;

    public GenreArtistInputDto(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int newId){
        this.id = newId;
    }
}
