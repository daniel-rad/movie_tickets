package org.drad.movie_tickets.dao;

import java.util.List;
import org.drad.movie_tickets.cache.Caches;
import org.drad.movie_tickets.domain.TicketEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    @Override
    @Cacheable(Caches.CACHE_TICKETS)
    List<TicketEntity> findAll();
}
