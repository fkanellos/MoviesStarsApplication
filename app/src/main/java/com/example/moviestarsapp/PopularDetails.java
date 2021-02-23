package com.example.moviestarsapp;

import java.util.Arrays;
import java.util.Objects;

public class PopularDetails {

    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String title;
    private String backdrop_path;
    private String original_language;
    private double popularity;
    private int vote_count;
    private Boolean video;
    private double vote_average;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopularDetails that = (PopularDetails) o;
        return getId() == that.getId() &&
                Double.compare(that.getPopularity(), getPopularity()) == 0 &&
                getVote_count() == that.getVote_count() &&
                Double.compare(that.getVote_average(), getVote_average()) == 0 &&
                Objects.equals(getPoster_path(), that.getPoster_path()) &&
                Objects.equals(getAdult(), that.getAdult()) &&
                Objects.equals(getOverview(), that.getOverview()) &&
                Objects.equals(getRelease_date(), that.getRelease_date()) &&
                Arrays.equals(getGenre_ids(), that.getGenre_ids()) &&
                Objects.equals(getOriginal_title(), that.getOriginal_title()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getBackdrop_path(), that.getBackdrop_path()) &&
                Objects.equals(getOriginal_language(), that.getOriginal_language()) &&
                Objects.equals(getVideo(), that.getVideo());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPoster_path(), getAdult(), getOverview(), getRelease_date(), getId(), getOriginal_title(), getTitle(), getBackdrop_path(), getOriginal_language(), getPopularity(), getVote_count(), getVideo(), getVote_average());
        result = 31 * result + Arrays.hashCode(getGenre_ids());
        return result;
    }

    @Override
    public String toString() {
        return "PopularDetails{" +
                "poster_path='" + poster_path + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                ", id=" + id +
                ", original_title='" + original_title + '\'' +
                ", title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", original_language='" + original_language + '\'' +
                ", popularity=" + popularity +
                ", vote_count=" + vote_count +
                ", video=" + video +
                ", vote_average=" + vote_average +
                '}';
    }
}

