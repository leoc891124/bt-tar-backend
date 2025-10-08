package com.leoc.springboot.bttar.services;

import com.leoc.springboot.bttar.model.ERole;
import com.leoc.springboot.bttar.model.Role;
import com.leoc.springboot.bttar.model.User;
import com.leoc.springboot.bttar.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendWelcomeEmail(SignupRequest user){
        SimpleMailMessage message = new SimpleMailMessage();

     /*   Set<String> strRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }*/

        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject("Bienvenido a BT Intermediaros App🚀");
        message.setText("Hola " + user.getFirstName()+" "+user.getLastName()+ ",\n\nTu cuenta fue creada exitosamente, username: "+ user.getUsername()+"\n\n¡Gracias por registrarte!");

        mailSender.send(message);

    }

}
