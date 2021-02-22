package com.mendes.controller;

import com.mendes.entity.Actor;
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

    private ActorService actorService;

    public UserActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("actors", actorService.list());
        return "user/actor-list";
    }

    @GetMapping("/add-actor")
    public String add(Model model) {
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "user/new-actor";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("actor") Actor actor) {
        actorService.save(actor);
        return "redirect:/user-actor/list";
    }
}
