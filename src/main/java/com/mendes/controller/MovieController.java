package com.mendes.controller;

import com.mendes.model.dto.MovieDto;
import com.mendes.model.entity.Movie;
import com.mendes.service.ActorService;
import com.mendes.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Controller
@RequestMapping("movie")
public class MovieController {

    private final MovieService movieService;
    private final ActorService actorService;

    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("movies", movieService.list());
        return "admin/movie-list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("movie") MovieDto movieDto) {
        movieService.save(movieDto);
        return "redirect:/movie/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        movieService.delete(id);
        return "redirect:/movie/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("actors", actorService.list());
        model.addAttribute("movie", movieService.getById(id));
        return "admin/new-movie";
    }

    @GetMapping("/add-movie")
    public String add(Model model) {
        model.addAttribute("actors", actorService.list());
        model.addAttribute("movie", new Movie());
        return "admin/new-movie";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("movies", movieService.search(search));
        return "admin/movie-list";
    }

    @GetMapping("/sort-date")
    public String sortDate(Model model) {
        model.addAttribute("movies", movieService.sortDate());
        return "admin/movie-list";
    }
}
