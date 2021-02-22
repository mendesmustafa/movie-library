package com.mendes.controller;

import com.mendes.entity.Actor;
import com.mendes.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Controller
@RequestMapping("actor")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Actor> actors = actorService.list();
        model.addAttribute("actors", actors);
        return "admin/actor-list";
    }

    @GetMapping("/add-actor")
    public String add(Model model) {
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "admin/new-actor";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        actorService.delete(id);
        return "redirect:/actor/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Actor actor = actorService.getById(id);
        model.addAttribute("actor", actor);
        return "admin/new-actor";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("actor") Actor actor) {
        actorService.save(actor);
        return "redirect:/actor/list";
    }
}
