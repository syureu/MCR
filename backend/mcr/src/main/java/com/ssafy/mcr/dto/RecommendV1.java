package com.ssafy.mcr.dto;

public class RecommendV1 {
    Long id;
    String posterPath;
    String title;

    public RecommendV1(Long id, String posterPath, String title) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
