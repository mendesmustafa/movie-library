package com.mendes.controller;

import com.mendes.model.dto.ActorDto;
import com.mendes.model.entity.Actor;
import com.mendes.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Controller
@RequestMapping("actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("actors", actorService.list());
        return "admin/actor-list";
    }

    @GetMapping("/add-actor")
    public String add(Model model) {
        model.addAttribute("actor", new Actor());
        return "admin/new-actor";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        actorService.delete(id);
        return "redirect:/actor/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("actor", actorService.getById(id));
        return "admin/new-actor";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("actor") ActorDto actorDto) {
        actorService.save(actorDto);
        return "redirect:/actor/list";
    }
}
