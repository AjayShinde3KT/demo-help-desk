package com.demo.helpdesk.app.controller;

import com.demo.helpdesk.app.model.Ticket;
import com.demo.helpdesk.app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Ticket createTicket(@RequestParam("employeeId") int employeeId,
                               @RequestParam("createdDate") String createdDate,
                               @RequestParam("subject") String subject,
                               @RequestParam("priority") String priority,
                               @RequestParam("category") String category,
                               @RequestPart("image") MultipartFile image,
                               @RequestParam("status") String status,
                               @RequestParam("description") String description) throws IOException {

        return ticketService.saveTicketWithImage(employeeId, createdDate, subject, priority, status, category, description, image);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllticktes();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<Ticket> search(@RequestParam("q") String query) {
        return ticketService.search(query, query);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Ticket> getTicketByEmployeeId(@RequestParam("empId") int empId) {
        return ticketService.getTicketByEmployeeId(empId);
    }



}
