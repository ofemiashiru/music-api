package com.afrobeatslib.musicApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;


@Entity
@Data
public class Genre {
    @Id
    @SequenceGenerator(
            name="genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    private int id;
    private String genreName;

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


}
