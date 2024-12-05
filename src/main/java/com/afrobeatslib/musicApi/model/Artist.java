package com.afrobeatslib.musicApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
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

    @ManyToMany
    @JoinTable(
        name="artist_genre",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Artist(){

    }

    public Artist(String artistName, String artistImageUrl, Set<Genre> genres){
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.genres = genres;
    }

    public Artist(UUID id, String artistName, String artistImageUrl){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
    }

    public Artist(UUID id, String artistName, String artistImageUrl, Set<Genre> genres){
        this.id = id;
        this.artistName = artistName;
        this.artistImageUrl = artistImageUrl;
        this.genres = genres;
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

    public Set<Genre> getArtistGenres(){
        return this.genres;
    }

    public void setArtistGenres(Set<Genre> newArtistGenres){
        this.genres = newArtistGenres;
    }

    public void addGenre(Genre genreToAdd){
        this.genres.add(genreToAdd);
    }

    public void removeGenre(Genre genreToRemove){
        this.genres.remove(genreToRemove);
    }

    @Override
    public String toString(){
        return String.format(
                "Artist {id=%s , artistName=%s, artistImageUrl=%s, artistGenre=%s}",
                id, artistName, artistImageUrl, genres
        );
    }
}
