package com.mendes.service;

import com.mendes.entity.Actor;
import com.mendes.entity.Movie;
import com.mendes.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findById(Long id) {
        Movie movie = null;
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movie = movieOptional.get();
        }
        return movie;
    }

    public Movie save(Movie model) {
        Movie movie = new Movie();
        if (model.getId() != null) {
            Optional<Movie> movieOptional = movieRepository.findById(model.getId());
            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            }
        }
        fill(movie, model);
        movieRepository.save(movie);
        return movie;
    }

    private void fill(Movie movie, Movie model) {
        movie.setName(model.getName());
        movie.setYear(model.getYear());
        movie.setType(model.getType());
        movie.setDescription(model.getDescription());
        movie.setMedia(model.getMedia());
        movie.setLanguage(model.getLanguage());
        movie.setActors(model.getActors());
    }

    public Movie getById(Long id) {
        Movie model = null;
        Movie movie = findById(id);
        if (movie != null) {
            model = fillModel(movie);
        }
        return model;
    }

    private Movie fillModel(Movie movie) {
        Movie model = new Movie();
        model.setId(movie.getId());
        model.setName(movie.getName());
        model.setYear(movie.getYear());
        model.setType(movie.getType());
        model.setDescription(movie.getDescription());
        model.setMedia(movie.getMedia());
        model.setLanguage(movie.getLanguage());
        model.setActors(movie.getActors());
        return model;
    }

    public void delete(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movieRepository.delete(movieOptional.get());
            System.out.println("Movie delete: " + id);
        } else {
            System.out.println("Movie did't found: " + id);
        }
    }

    public List<Movie> list() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public List<Movie> search(String search) {
        List<Movie> movies = movieRepository.findBySearch(search.toUpperCase());
        return movies;
    }

    public List<Movie> sortDate() {
        List<Movie> movies = movieRepository.findAllByDate();
        return movies;
    }

}
