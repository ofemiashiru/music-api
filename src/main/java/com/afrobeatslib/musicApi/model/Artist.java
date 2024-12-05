package com.afrobeatslib.musicApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Artist {
    @Id
    @SequenceGenerator(
            name="artist_sequence",
            sequenceName = "artist_sequence",
            allocationSize = 1
    )
    private UUID id;

    private String artistName;
    private String artistImageUrl;
    private int artistGenreId;

    public Artist(){

    }
    public Artist(String artistName, String artistImageUrl, int artistGenreId){
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.artistGenreId = artistGenreId;
    }

    public Artist(UUID id, String artistName, String artistImageUrl, int artistGenreId){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.artistGenreId = artistGenreId;
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID newId){
        this.id = newId;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public void setArtistName(String newName){
        this.artistName = newName;
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

    @Override
    public String toString(){
        return String.format(
                "Artist {id=%s , artistName=%s, artistImageUrl=%s, artistGenre=%s}",
                id, artistName, artistImageUrl, artistGenreId
        );
    }
}
