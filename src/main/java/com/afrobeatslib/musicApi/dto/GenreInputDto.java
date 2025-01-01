package com.afrobeatslib.musicApi.dto;

public class GenreInputDto {

    private String genreName;

    public GenreInputDto(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public void setGenreName(String newGenere) {
        this.genreName = newGenere;
    }
}
