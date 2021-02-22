package com.mendes.service;

import com.mendes.entity.Actor;
import com.mendes.entity.Movie;
import com.mendes.enums.ActorRole;
import com.mendes.enums.MovieLanguage;
import com.mendes.enums.MovieType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 21.02.2021.
 */

@SpringBootTest
public class MovieServiceTest {


    private final static String DEFAULT_FIRST_NAME = "ALİ";
    private final static String DEFAULT_LAST_NAME = "CAN";
    private final static ActorRole DEFAULT_ROLE = ActorRole.BASROL;

    private final static String DEFAULT_NAME = "MURAT";
    private final static int DEFAULT_YEAR = 2020;
    private final static MovieType DEFAULT_TYPE = MovieType.COMEDY;
    private final static String DEFAULT_DESCRIPTION = "COMEDİ FİLMİ";
    private final static String DEFAULT_MEDIA = "MEDYA";
    private final static MovieLanguage DEFAULT_LANGUAGE = MovieLanguage.ENGLISH;


    Actor defaultActor;
    Actor resultActor;
    List<Actor> actorList;

    Movie defaultMovie;
    Movie resultMovie;

    @Autowired
    private ActorService actorService;
    @Autowired
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        actorList = new ArrayList<>();
        defaultActor = new Actor();
        defaultActor.setFirstName(DEFAULT_FIRST_NAME);
        defaultActor.setLastName(DEFAULT_LAST_NAME);
        defaultActor.setRole(DEFAULT_ROLE);
        resultActor = actorService.save(defaultActor);
        actorList.add(defaultActor);

        defaultMovie = new Movie();
        defaultMovie.setName(DEFAULT_NAME);
        defaultMovie.setYear(DEFAULT_YEAR);
        defaultMovie.setType(DEFAULT_TYPE);
        defaultMovie.setDescription(DEFAULT_DESCRIPTION);
        defaultMovie.setMedia(DEFAULT_MEDIA);
        defaultMovie.setLanguage(DEFAULT_LANGUAGE);
        defaultMovie.setActors(actorList);
    }

    @AfterEach
    public void clear() {
        if (resultMovie != null && resultMovie.getId() != null) {
            movieService.delete(resultMovie.getId());
            actorService.delete(resultActor.getId());
        }
    }

    @Test
    public void create() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie.getId());
    }

    @Test
    public void delete() {
        resultMovie = movieService.save(defaultMovie);
        movieService.delete(resultMovie.getId());
        Movie movie = movieService.getById(resultMovie.getId());
        assertNull(movie);
    }

    @Test
    public void findById() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        Movie movie = movieService.getById(resultMovie.getId());
        assertNotNull(movie);
        assertAll(
                () -> assertEquals(resultMovie.getId(), movie.getId()),
                () -> assertEquals(resultMovie.getName(), movie.getName()),
                () -> assertEquals(resultMovie.getYear(), movie.getYear()),
                () -> assertEquals(resultMovie.getType(), movie.getType()),
                () -> assertEquals(resultMovie.getDescription(), movie.getDescription()),
                () -> assertEquals(resultMovie.getMedia(), movie.getMedia()),
                () -> assertEquals(resultMovie.getLanguage(), movie.getLanguage())
        );
    }

    @Test
    public void list() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        List<Movie> movies = movieService.list();
        assertEquals(movies.size(), 1);
    }
}
