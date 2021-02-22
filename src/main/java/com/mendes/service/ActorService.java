package com.mendes.service;

import com.mendes.entity.Actor;
import com.mendes.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Service
public class ActorService {

    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor findById(Long id) {
        Actor actor = null;
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            actor = actorOptional.get();
        }
        return actor;
    }

    public Actor save(Actor model) {
        Actor actor = new Actor();
        if (model.getId() != null) {
            Optional<Actor> actorOptional = actorRepository.findById(model.getId());
            if (actorOptional.isPresent()) {
                actor = actorOptional.get();
            }
        }
        fill(actor, model);
        actorRepository.save(actor);
        return actor;
    }

    private void fill(Actor actor, Actor model) {
        actor.setFirstName(model.getFirstName());
        actor.setLastName(model.getLastName());
        actor.setRole(model.getRole());
    }

    public Actor getById(Long id) {
        Actor model = null;
        Actor actor = findById(id);
        if (actor != null) {
            model = fillModel(actor);
        }
        return model;
    }

    private Actor fillModel(Actor actor) {
        Actor model = new Actor();
        model.setId(actor.getId());
        model.setFirstName(actor.getFirstName());
        model.setLastName(actor.getLastName());
        model.setRole(actor.getRole());
        return model;
    }

    public void delete(Long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            actorRepository.delete(actorOptional.get());
            System.out.println("Actor deleted: " + id);
        } else {
            System.out.println("Actor did't found : " + id);
        }
    }

    public List<Actor> list() {
        List<Actor> actors = actorRepository.findAll();
        return actors;
    }
}
