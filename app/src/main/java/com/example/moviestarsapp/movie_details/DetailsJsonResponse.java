package com.example.moviestarsapp.movie_details;

import java.util.Objects;

public class DetailsJsonResponse {


    private String overview;
    private String poster_path;
    private String release_date;
    private int runtime;
    private String status;
    private double vote_average;
    private String original_title;
    private String title;

    public void setPosterPrefixPath(String prefix) {
        poster_path = prefix + poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsJsonResponse that = (DetailsJsonResponse) o;
        return runtime == that.runtime &&
                Double.compare(that.vote_average, vote_average) == 0 &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(poster_path, that.poster_path) &&
                Objects.equals(release_date, that.release_date) &&
                Objects.equals(status, that.status) &&
                Objects.equals(original_title, that.original_title) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(overview, poster_path, release_date, runtime, status, vote_average, original_title, title);
    }

    @Override
    public String toString() {
        return "DetailsJsonResponse{" +
                "overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", vote_average=" + vote_average +
                ", original_title='" + original_title + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
