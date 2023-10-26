package com.demo.helpdesk.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int employeeId;
    private String ticketNo;
    private String createdDate;
    private String subject;
    private String priority;
    private String category;
    private String status;
    private String description;
    private String imageUrl;
}
