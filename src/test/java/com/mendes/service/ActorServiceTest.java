package com.mendes.service;

import com.mendes.entity.Actor;
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
public class ActorServiceTest {

    private final static String DEFAULT_FIRST_NAME = "ALİ";
    private final static String DEFAULT_LAST_NAME = "CAN";
    private final static ActorRole DEFAULT_ROLE = ActorRole.BASROL;

    Actor defaultActor;
    Actor resultActor;

    @Autowired
    private ActorService actorService;

    @BeforeEach
    public void setUp() {
        defaultActor = new Actor();
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
    public void create() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor.getId());
    }

    @Test
    public void delete() {
        resultActor = actorService.save(defaultActor);
        actorService.delete(resultActor.getId());
        Actor actor = actorService.findById(resultActor.getId());
        assertNull(actor);
    }

    @Test
    public void findById() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor);
        Actor actor = actorService.getById(resultActor.getId());
        assertNotNull(actor);
        assertAll(
                () -> assertEquals(actor.getId(), resultActor.getId()),
                () -> assertEquals(actor.getFirstName(), resultActor.getFirstName()),
                () -> assertEquals(actor.getLastName(), resultActor.getLastName()),
                () -> assertEquals(actor.getRole(), resultActor.getRole())
        );

    }

    @Test
    public void list() {
        resultActor = actorService.save(defaultActor);
        assertNotNull(resultActor);
        Actor actor = new Actor();
        actor.setFirstName("MURAT");
        actor.setLastName("TAN");
        actor.setRole(ActorRole.KONUK);
        Actor actor2 = actorService.save(actor);
        assertNotNull(actor2);

        List<Actor> actors = actorService.list();
        assertEquals(actors.size(), 2);
    }
}
