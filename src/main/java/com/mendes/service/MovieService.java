package com.mendes.service;

import com.mendes.model.dto.MovieDto;
import com.mendes.model.entity.Movie;
import com.mendes.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Service
public class MovieService {

    private final MovieRepository movieRepository;

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

    public MovieDto save(MovieDto movieDto) {
        Movie movie = new Movie();
        if (movieDto.getId() != null) {
            Optional<Movie> movieOptional = movieRepository.findById(movieDto.getId());
            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            }
        }
        fillMovie(movie, movieDto);
        movieRepository.save(movie);
        movieDto.setId(movie.getId());
        return movieDto;
    }

    private void fillMovie(Movie movie, MovieDto movieDto) {
        movie.setName(movieDto.getName());
        movie.setYear(movieDto.getYear());
        movie.setType(movieDto.getType());
        movie.setDescription(movieDto.getDescription());
        movie.setMedia(movieDto.getMedia());
        movie.setLanguage(movieDto.getLanguage());
        movie.setActors(movieDto.getActors());
    }

    public MovieDto getById(Long id) {
        MovieDto model = null;
        Movie movie = findById(id);
        if (movie != null) {
            model = fillMovieDto(movie);
        }
        return model;
    }

    private MovieDto fillMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setYear(movie.getYear());
        movieDto.setType(movie.getType());
        movieDto.setDescription(movie.getDescription());
        movieDto.setMedia(movie.getMedia());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setActors(movie.getActors());
        return movieDto;
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

    public List<MovieDto> list() {
        return movieRepository.findAll().stream().map(this::fillMovieDto).collect(Collectors.toList());
    }

    public List<MovieDto> search(String search) {
        return movieRepository.findBySearch(search.toUpperCase()).stream().map(this::fillMovieDto).collect(Collectors.toList());
    }

    public List<MovieDto> sortDate() {
        return movieRepository.findAllByDate().stream().map(this::fillMovieDto).collect(Collectors.toList());
    }

}
