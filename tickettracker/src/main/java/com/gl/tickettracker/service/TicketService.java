package com.gl.tickettracker.service;

import com.gl.tickettracker.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> getAllTickets();

    Optional<Ticket> getTicketById(Long id);

    void saveTicket(Ticket ticket);

    void deleteTicket(Long id);

    List<Ticket> search(String query);

    Ticket view(Long id);

    void updateTicket(Long id, Ticket updatedTicket);

    // Can add other service methods as needed
}
