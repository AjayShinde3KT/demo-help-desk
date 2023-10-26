package com.demo.helpdesk.app.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.demo.helpdesk.app.model.Ticket;
import com.demo.helpdesk.app.repository.TicketRepository;
import com.demo.helpdesk.app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BlobServiceClient blobServiceClient;

    @Value("${azure.blob.containerName}")
    private String blobContainerName;

    @Override
    public Ticket saveTicketWithImage(int employeeId, String createdDate, String subject, String priority, String status, String category, String description, MultipartFile image) throws IOException {
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(blobContainerName).getBlobClient(image.getOriginalFilename());
        blobClient.upload(image.getInputStream(), image.getSize(), true);
        String imageUrl = blobClient.getBlobUrl();

        String ticketNumber = generateTicketNumber();
        Ticket ticket = new Ticket();
        ticket.setEmployeeId(employeeId);
        ticket.setCreatedDate(createdDate);
        ticket.setTicketNo(ticketNumber);
        ticket.setSubject(subject);
        ticket.setPriority(priority);
        ticket.setCategory(category);
        ticket.setStatus(status);
        ticket.setDescription(description);
        ticket.setImageUrl(imageUrl);
        ticketRepository.save(ticket);
        return ticket;
    }

    public String generateTicketNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String ticketNumber = dateFormat.format(new Date());
        return ticketNumber;
    }

    @Override
    public List<Ticket> getAllticktes() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> search(String subject, String description) {
        return ticketRepository.findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(subject, description);
    }

    @Override
    public List<Ticket> getTicketByEmployeeId(int empId) {
        return ticketRepository.findByEmployeeId(empId);
    }
}
