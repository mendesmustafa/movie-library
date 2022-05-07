package com.mendes.controller;

import com.mendes.model.dto.MovieDto;
import com.mendes.model.entity.Movie;
import com.mendes.service.ActorService;
import com.mendes.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mendesmustafa on 22.02.2021.
 */

@Controller
@RequestMapping("user-movie")
public class UserMovieController {

    private final MovieService movieService;
    private final ActorService actorService;

    public UserMovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("movies", movieService.list());
        return "user/movie-list";
    }

    @GetMapping("/add-movie")
    public String add(Model model) {
        model.addAttribute("actors", actorService.list());
        model.addAttribute("movie", new Movie());
        return "user/new-movie";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("movie") MovieDto movieDto) {
        movieService.save(movieDto);
        return "redirect:/user-movie/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("movies", movieService.search(search));
        return "user/movie-list";
    }

    @GetMapping("/sort-date")
    public String sortDate(Model model) {
        model.addAttribute("movies", movieService.sortDate());
        return "user/movie-list";
    }
}
