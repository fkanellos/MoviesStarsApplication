package com.example.moviestarsapp;

import java.util.Objects;

public class MovieModel {

//    private String img;
    private String title;
    private int year;
    private String genre;
    private String duration;
//    private String cast;
//    private String director;
//    private String summary;
//    private Boolean favorite;

    public MovieModel(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public MovieModel(String title, int year, String genre, String duration) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieModel that = (MovieModel) o;
        return getTitle().equals(that.getTitle()) &&
                getYear()==that.getYear() &&
                getGenre().equals(that.getGenre()) &&
                getDuration()==that.getDuration();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear(), getGenre(), getDuration());
    }
}
