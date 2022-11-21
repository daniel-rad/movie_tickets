package org.drad.movie_tickets.dao;

import java.util.Optional;
import org.drad.movie_tickets.cache.Caches;
import org.drad.movie_tickets.domain.GroupDiscountEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDiscountRepository extends JpaRepository<GroupDiscountEntity, Integer> {

    @Cacheable(Caches.CACHE_GROUP_DISCOUNTS)
    Optional<GroupDiscountEntity> findByTicketId(Integer ticketId);
}
