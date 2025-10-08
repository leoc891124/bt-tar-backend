package com.leoc.springboot.bttar.controller;


import com.leoc.springboot.bttar.model.User;
import com.leoc.springboot.bttar.payload.request.SignupRequest;
import com.leoc.springboot.bttar.payload.response.MessageResponse;
import com.leoc.springboot.bttar.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://evelyn-rental.netlify.app"
})
@RestController
@RequestMapping("/bttar/v1.0/auth")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> sendWelcomeEmail(@RequestBody SignupRequest user) throws MessagingException {
        emailService.sendWelcomeEmail(user);
        return ResponseEntity.ok(new MessageResponse("Correo enviado exitosamente"));
    }

}
