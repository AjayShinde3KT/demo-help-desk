package com.demo.helpdesk.app.service;

import com.demo.helpdesk.app.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TicketService {

    Ticket saveTicketWithImage(int employeeId,String createdDate, String subject, String priority,String status, String category, String description, MultipartFile image) throws IOException;

    List<Ticket> getAllticktes();

    List<Ticket> search(String subject,String description);

    List<Ticket> getTicketByEmployeeId(int empId);
}
