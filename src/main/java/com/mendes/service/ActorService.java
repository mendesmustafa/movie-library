package com.mendes.service;

import com.mendes.model.dto.ActorDto;
import com.mendes.model.entity.Actor;
import com.mendes.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Service
public class ActorService {

    private final ActorRepository actorRepository;

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

    public ActorDto save(ActorDto actorDto) {
        Actor actor = new Actor();
        if (actorDto.getId() != null) {
            Optional<Actor> actorOptional = actorRepository.findById(actorDto.getId());
            if (actorOptional.isPresent()) {
                actor = actorOptional.get();
            }
        }
        fillActor(actor, actorDto);
        actorRepository.save(actor);
        actorDto.setId(actor.getId());
        return actorDto;
    }

    private void fillActor(Actor actor, ActorDto actorDto) {
        actor.setFirstName(actorDto.getFirstName());
        actor.setLastName(actorDto.getLastName());
        actor.setRole(actorDto.getRole());
    }

    public ActorDto getById(Long id) {
        ActorDto model = null;
        Actor actor = findById(id);
        if (actor != null) {
            model = fillActorDto(actor);
        }
        return model;
    }

    private ActorDto fillActorDto(Actor actor) {
        ActorDto model = new ActorDto();
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

    public List<ActorDto> list() {
        return actorRepository.findAll().stream().map(this::fillActorDto).collect(Collectors.toList());
    }
}
