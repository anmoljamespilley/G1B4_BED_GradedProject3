package com.gl.tickettracker.serviceimpl;

import com.gl.tickettracker.exception.TicketNotFoundException;
import com.gl.tickettracker.model.Ticket;
import com.gl.tickettracker.repository.TicketRepository;
import com.gl.tickettracker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> search(String query) {
        // Implement search logic based on the provided query
        return ticketRepository.findByTitleContainingOrShortDescriptionContaining(query, query);
    }

    @Override
    public Ticket view(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);

        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            // Throw custom exception when the ticket with the given id is not found
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }
    }

    @Override
    public void updateTicket(Long id, Ticket updatedTicket) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);

        if (existingTicket.isPresent()) {
            Ticket ticketToUpdate = existingTicket.get();
            // Update the properties of the existing ticket with the values from updatedTicket
            ticketToUpdate.setTitle(updatedTicket.getTitle());
            ticketToUpdate.setShortDescription(updatedTicket.getShortDescription());
            ticketToUpdate.setContent(updatedTicket.getContent()); // Add this line to update content
            // Set other properties as needed

            // Save the updated ticket
            ticketRepository.save(ticketToUpdate);
        } else {
            // Throw custom exception when the ticket with the given id is not found
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }
    }

    // Can add other service methods as needed
}
