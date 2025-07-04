package com.leoc.springboot.rentalevelyn.services;

import com.leoc.springboot.rentalevelyn.model.Item;
import com.leoc.springboot.rentalevelyn.model.Rental;
import com.leoc.springboot.rentalevelyn.model.RentalItem;
import com.leoc.springboot.rentalevelyn.payload.response.MessageResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private ItemService itemService;

    public void sendRentalEmail(Rental rentalEmail) throws MessagingException{

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(rentalEmail.getEmail());
        helper.setSubject("Rental service: "+rentalEmail.getName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String initDateDateFormatted = sdf.format(rentalEmail.getInitalDate());
        String finalDateFormatted = sdf.format(rentalEmail.getFinalDate());

        helper.setText(buildEmailRentalHtml(rentalEmail.getEmail(), initDateDateFormatted, finalDateFormatted, rentalEmail.getItems()), true); // true = HTML

        mailSender.send(message);

    }

    public String buildEmailRentalHtml(String name, String initalDate,
                                 String finalDate, List<RentalItem> rentalItems) {
        StringBuilder itemsHtml = new StringBuilder();
        double total = 0.0;

        for (RentalItem rentalItem : rentalItems) {

            Optional<Item> item = itemService.getItemById(rentalItem.getItemId());

            if(item.isEmpty()){
                return "Error retriving items";
            }



            double subtotal = rentalItem.getQuantitySelected() * item.get().getPrice();
            total += subtotal;

            itemsHtml.append("""
            <tr>
              <td>%s</td>
              <td style="text-align:center;">%d</td>
              <td style="text-align:right;">$%.2f</td>
              <td style="text-align:right;">$%.2f</td>
            </tr>
        """.formatted(
                    item.get().getName(),
                    rentalItem.getQuantitySelected(),
                    item.get().getPrice(),
                    subtotal
            ));
        }

        // Fila de total general
        itemsHtml.append("""
        <tr style="background-color:#f0f0f0; font-weight:bold;">
          <td colspan="3" style="text-align:right;">Total:</td>
          <td style="text-align:right;">$%.2f</td>
        </tr>
    """.formatted(total));

        // HTML final
        String html = """
        <html>
          <body style="font-family:Arial,sans-serif;">
            <h2>Hi %s,</h2>
            <p>You rental service was booked in these dates: %s to %s </p>
            <table border="1" cellpadding="8" cellspacing="0" style="border-collapse:collapse; width:100%%;">
              <thead style="background-color:#e8e8e8;">
                <tr>
                  <th>Product</th>
                  <th>Quantity Selected</th>
                  <th>Unity price</th>
                  <th>Subtotal</th>
                </tr>
              </thead>
              <tbody>
                %s
              </tbody>
            </table>
            <p style="margin-top:20px;">Regards,<br>Evelyn's rental team</p>
          </body>
        </html>
    """.formatted(name, initalDate, finalDate, itemsHtml);

        return html;
    }
}