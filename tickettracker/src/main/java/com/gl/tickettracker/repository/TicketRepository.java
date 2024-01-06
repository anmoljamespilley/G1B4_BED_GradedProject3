package com.gl.tickettracker.repository;

import com.gl.tickettracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTitleContainingOrShortDescriptionContaining(String titleQuery, String descriptionQuery);
    // Can add custom query methods if needed
}
