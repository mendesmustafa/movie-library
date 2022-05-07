package com.mendes.service;

import com.mendes.model.dto.ActorDto;
import com.mendes.model.entity.Actor;
import com.mendes.enums.ActorRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 21.02.2021.
 */

@SpringBootTest
class ActorServiceTest {

    private final static String DEFAULT_FIRST_NAME = "TEST-FIRST_NAME";
    private final static String DEFAULT_LAST_NAME = "TEST-LAST_NAME";
    private final static ActorRole DEFAULT_ROLE = ActorRole.LEAD_ACTOR;

    ActorDto defaultActor;
    ActorDto resultActor;

    @Autowired
    private ActorService actorService;

    @BeforeEach
    public void setUp() {
        defaultActor = new ActorDto();
        defaultActor.setFirstName(DEFAULT_FIRST_NAME);
        defaultActor.setLastName(DEFAULT_LAST_NAME);
        defaultActor.setRole(DEFAULT_ROLE);
    }

    @AfterEach
    public void clear() {
        if (resultActor != null && resultActor.getId() != null) {
            actorService.delete(resultActor.getId());
        }
    }

    @Test
    void create() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor.getId());
    }

    @Test
    void delete() {
        resultActor = actorService.save(defaultActor);
        actorService.delete(resultActor.getId());
        Actor actor = actorService.findById(resultActor.getId());
        assertNull(actor);
    }

    @Test
    void findById() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor);
        ActorDto actor = actorService.getById(resultActor.getId());
        assertAll(
                () -> assertNotNull(actor),
                () -> assertEquals(actor.getId(), resultActor.getId()),
                () -> assertEquals(actor.getFirstName(), resultActor.getFirstName()),
                () -> assertEquals(actor.getLastName(), resultActor.getLastName()),
                () -> assertEquals(actor.getRole(), resultActor.getRole())
        );
    }

    @Test
    void list() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor);
        List<ActorDto> actors = actorService.list();
        assertTrue(actors.size() > 0);
    }
}
