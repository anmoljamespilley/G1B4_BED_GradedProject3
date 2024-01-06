package com.gl.tickettracker.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Short description cannot be blank")
    private String shortDescription;

    @Lob // Large Object for storing large amounts of text
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    // Getters and Setters
}
