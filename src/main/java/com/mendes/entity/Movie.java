package com.mendes.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mendes.enums.MovieLanguage;
import com.mendes.enums.MovieType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Entity
@Table(name = "MOVIE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Movie.class)
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "MOVIE_ID_SEQ")
    @SequenceGenerator(name = "MOVIE_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private MovieType type = MovieType.COMEDY;

    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Column(name = "MEDIA")
    private String media;

    @Column(name = "LANGUAGE")
    @Enumerated(EnumType.STRING)
    private MovieLanguage language = MovieLanguage.ENGLISH;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID")
    )
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