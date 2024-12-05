package com.afrobeatslib.musicApi.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;


@Entity
public class Genre {
    @Id
    @SequenceGenerator(
            name="genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    private int id;
    private String genreName;

    @ManyToMany(mappedBy="genres")
    private Set<Artist> artists = new TreeSet<>();

    public Genre(){

    }

    public Genre(int id, String genreName){
        this.id = id;
        this.genreName = genreName;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public String getGenreName(){
        return this.genreName;
    }

    public void setGenreName(String newGenreName){
        this.genreName = newGenreName;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }


}
