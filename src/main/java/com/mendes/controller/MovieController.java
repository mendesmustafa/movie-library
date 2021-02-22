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
 * Created by mendesmustafa on 20.02.2021.
 */

@Controller
@RequestMapping("movie")
public class MovieController {

    private MovieService movieService;
    private ActorService actorService;

    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Movie> movies = movieService.list();
        model.addAttribute("movies", movies);
        return "admin/movie-list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("movie") Movie movie) {
        movieService.save(movie);
        return "redirect:/movie/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        movieService.delete(id);
        return "redirect:/movie/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.getById(id);
        model.addAttribute("actors", actorService.list());
        model.addAttribute("movie", movie);
        return "admin/new-movie";
    }

    @GetMapping("/add-movie")
    public String add(Model model) {
        Movie movie = new Movie();
        List<Actor> actors = actorService.list();
        model.addAttribute("actors", actors);
        model.addAttribute("movie", movie);
        return "admin/new-movie";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Movie> movies = movieService.search(search);
        model.addAttribute("movies", movies);
        return "admin/movie-list";
    }

    @GetMapping("/sort-date")
    public String sortDate(Model model) {
        List<Movie> movies = movieService.sortDate();
        model.addAttribute("movies", movies);
        return "admin/movie-list";
    }
}
