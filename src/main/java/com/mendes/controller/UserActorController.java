package com.mendes.controller;

import com.mendes.model.dto.ActorDto;
import com.mendes.model.entity.Actor;
import com.mendes.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mendesmustafa on 22.02.2021.
 */

@Controller
@RequestMapping("user-actor")
public class UserActorController {

    private final ActorService actorService;

    public UserActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("actors", actorService.list());
        return "user/actor-list";
    }

    @GetMapping("/add-actor")
    public String add(Model model) {
        model.addAttribute("actor", new Actor());
        return "user/new-actor";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("actor") ActorDto actorDto) {
        actorService.save(actorDto);
        return "redirect:/user-actor/list";
    }
}
