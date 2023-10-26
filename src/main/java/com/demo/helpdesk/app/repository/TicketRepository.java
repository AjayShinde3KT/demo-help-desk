package com.demo.helpdesk.app.repository;

import com.demo.helpdesk.app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String subject,String description);
    List<Ticket> findByEmployeeId(int empId);
}
