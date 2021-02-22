package com.mendes.controller;

import com.mendes.entity.Actor;
import com.mendes.entity.Movie;
import com.mendes.service.ActorService;
import com.mendes.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mendesmustafa on 22.02.2021.
 */

@Controller
@RequestMapping("user-movie")
public class UserMovieController {

    private MovieService movieService;
    private ActorService actorService;

    public UserMovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Movie> movies = movieService.list();
        model.addAttribute("movies", movies);
        return "user/movie-list";
    }

    @GetMapping("/add-movie")
    public String add(Model model) {
        Movie movie = new Movie();
        List<Actor> actors = actorService.list();
        model.addAttribute("actors", actors);
        model.addAttribute("movie", movie);
        return "user/new-movie";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("movie") Movie movie) {
        movieService.save(movie);
        return "redirect:/user-movie/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Movie> movies = movieService.search(search);
        model.addAttribute("movies", movies);
        return "user/movie-list";
    }

    @GetMapping("/sort-date")
    public String sortDate(Model model) {
        List<Movie> movies = movieService.sortDate();
        model.addAttribute("movies", movies);
        return "user/movie-list";
    }
}
