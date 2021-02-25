package com.example.moviestarsapp.shared.json;

public class ImageConfigurations {

    private String base_url;
    private String secure_base_url;
    private String[] poster_sizes;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getSecure_base_url() {
        return secure_base_url;
    }

    public void setSecure_base_url(String secure_base_url) {
        this.secure_base_url = secure_base_url;
    }

    public String[] getPoster_sizes() {
        return poster_sizes;
    }

    public void setPoster_sizes(String[] poster_sizes) {
        this.poster_sizes = poster_sizes;
    }
}
