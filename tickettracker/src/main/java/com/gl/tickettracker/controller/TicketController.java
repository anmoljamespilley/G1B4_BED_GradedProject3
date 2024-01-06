package com.gl.tickettracker.controller;

import com.gl.tickettracker.model.Ticket;
import com.gl.tickettracker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String query, Model model) {
        try {
            List<Ticket> tickets;
            if (query != null && !query.isEmpty()) {
                tickets = ticketService.search(query);
            } else {
                tickets = ticketService.getAllTickets();
            }
            model.addAttribute("tickets", tickets);
            model.addAttribute("searchQuery", query); // Pass the search query to the view
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching tickets: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @GetMapping("/create")
    public String createTicketForm(Model model) {
        try {
            model.addAttribute("formHeading", "Create a New Ticket");
            return "create";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating ticket: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @PostMapping("/create")
    public String createTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        try {
            ticket.setCreatedOn(new Date());
            ticket.setContent(ticket.getContent());
            ticketService.saveTicket(ticket);
            return "redirect:/tickets/home";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating ticket: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/view")
    public String viewTicket(@PathVariable Long id, Model model) {
        try {
            Ticket ticket = ticketService.view(id);
            model.addAttribute("ticket", ticket);
            return "view";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching ticket details: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String editTicketForm(@PathVariable Long id, Model model) {
        try {
            Optional<Ticket> ticket = ticketService.getTicketById(id);
            ticket.ifPresent(value -> model.addAttribute("ticket", value));
            return "edit";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading ticket for editing: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String updateTicket(@ModelAttribute("ticket") Ticket ticket, Model model) {
        try {
            ticketService.updateTicket(ticket.getId(), ticket);
            model.addAttribute("successMessage", "Ticket updated successfully");
            return "redirect:/tickets/home";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating ticket: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id, Model model) {
        try {
            ticketService.deleteTicket(id);
            return "redirect:/tickets/home";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting ticket: " + e.getLocalizedMessage());
            return "error";
        }
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        try {
            List<Ticket> tickets = ticketService.search(query);
            model.addAttribute("tickets", tickets);
            model.addAttribute("searchQuery", query); // Pass the search query to the view
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Error searching for tickets: " + e.getLocalizedMessage());
            return "error";
        }
    }
}
