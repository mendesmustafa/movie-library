package com.mendes.service;

import com.mendes.model.dto.MovieDto;
import com.mendes.enums.MovieLanguage;
import com.mendes.enums.MovieType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 21.02.2021.
 */

@SpringBootTest
class MovieServiceTest {

    private final static String DEFAULT_NAME = "TEST-NAME";
    private final static int DEFAULT_YEAR = 2020;
    private final static MovieType DEFAULT_TYPE = MovieType.COMEDY;
    private final static String DEFAULT_DESCRIPTION = "TEST-DESCRIPTION";
    private final static String DEFAULT_MEDIA = "TEST-MEDIA";
    private final static MovieLanguage DEFAULT_LANGUAGE = MovieLanguage.ENGLISH;

    MovieDto defaultMovie;
    MovieDto resultMovie;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        defaultMovie = new MovieDto();
        defaultMovie.setName(DEFAULT_NAME);
        defaultMovie.setYear(DEFAULT_YEAR);
        defaultMovie.setType(DEFAULT_TYPE);
        defaultMovie.setDescription(DEFAULT_DESCRIPTION);
        defaultMovie.setMedia(DEFAULT_MEDIA);
        defaultMovie.setLanguage(DEFAULT_LANGUAGE);
    }

    @AfterEach
    public void clear() {
        if (resultMovie != null && resultMovie.getId() != null) {
            movieService.delete(resultMovie.getId());
        }
    }

    @Test
    void create() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie.getId());
    }

    @Test
    void delete() {
        resultMovie = movieService.save(defaultMovie);
        movieService.delete(resultMovie.getId());
        MovieDto movie = movieService.getById(resultMovie.getId());
        assertNull(movie);
    }

    @Test
    void findById() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        MovieDto movie = movieService.getById(resultMovie.getId());
        assertAll(
                () -> assertNotNull(movie),
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
    void list() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        List<MovieDto> movies = movieService.list();
        assertTrue(movies.size() > 0);
    }

    @Test
    void search() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        List<MovieDto> movies = movieService.search(resultMovie.getName());
        assertEquals(resultMovie.getName(), movies.get(0).getName());
    }

    @Test
    void sortDate() {
        resultMovie = movieService.save(defaultMovie);
        assertNotNull(resultMovie);
        List<MovieDto> movies = movieService.sortDate();
        assertEquals(resultMovie.getYear(), movies.get(0).getYear());
    }
}
