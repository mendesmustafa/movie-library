package com.mendes.repository;

import com.mendes.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mendesmustafa on 20.02.2021.
 */

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
