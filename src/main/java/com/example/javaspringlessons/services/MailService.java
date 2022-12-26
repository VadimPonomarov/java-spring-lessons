package com.example.javaspringlessons.services;

import com.example.javaspringlessons.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void send(Customer customer) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(customer.getEmail());
            helper.setText(
                    "<a href='http://localhost:8080/customers/activate/" + customer.getActivationToken().getToken() + "'>click to activate</a>", true);
            helper.setFrom(new InternetAddress(customer.getEmail()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(mimeMessage);
    }
}
