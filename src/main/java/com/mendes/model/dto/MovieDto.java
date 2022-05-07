package com.mendes.model.dto;

import com.mendes.enums.MovieLanguage;
import com.mendes.enums.MovieType;
import com.mendes.model.entity.Actor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mendesmustafa on 23-04-2022
 */

public class MovieDto implements Serializable {

    private Long id;
    private String name;
    private int year;
    private MovieType type;
    private String description;
    private String media;
    private MovieLanguage language;
    private List<Actor> actors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public MovieLanguage getLanguage() {
        return language;
    }

    public void setLanguage(MovieLanguage language) {
        this.language = language;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
