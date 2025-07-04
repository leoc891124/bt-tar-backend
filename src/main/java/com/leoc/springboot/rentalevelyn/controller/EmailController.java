package com.leoc.springboot.rentalevelyn.controller;
import com.leoc.springboot.rentalevelyn.model.Rental;
import com.leoc.springboot.rentalevelyn.payload.request.EmailRequest;
import com.leoc.springboot.rentalevelyn.payload.response.MessageResponse;
import com.leoc.springboot.rentalevelyn.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rentalevelyn/v1.0/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/sendRental")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> sendEmail(@RequestBody Rental request) throws MessagingException {
        emailService.sendRentalEmail(request);
        return ResponseEntity.ok(new MessageResponse("Correo enviado exitosamente"));
    }

   /* @PostMapping("/sendHtml")
    public ResponseEntity<?> sendEmailHtml(@RequestBody EmailRequest request) throws MessagingException {
        emailService.sendHtmlEmail(request.getTo(), request.getSubject(), request.getName(), request.getInitalDate(),
                request.getFinalDate() ,request.getItems());
        return ResponseEntity.ok("Correo con HTML enviado correctamente");
    }*/




}
