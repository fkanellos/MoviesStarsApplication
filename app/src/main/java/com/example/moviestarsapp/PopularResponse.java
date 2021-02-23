package com.example.moviestarsapp;

import java.util.List;
import java.util.Objects;

public class PopularResponse {

    private int page;
    private List<PopularDetails> results;
    private int total_results;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PopularDetails> getResults() {
        return results;
    }

    public void setResults(List<PopularDetails> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopularResponse that = (PopularResponse) o;
        return getPage() == that.getPage() &&
                getTotal_results() == that.getTotal_results() &&
                getTotal_pages() == that.getTotal_pages() &&
                Objects.equals(getResults(), that.getResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), getResults(), getTotal_results(), getTotal_pages());
    }

    @Override
    public String toString() {
        return "PopularResponse{" +
                "page=" + page +
                ", results=" + results +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                '}';
    }
}
