package com.exe201.tutorlink.main.client;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendGridClient {
    @Value("${sendgrid.api.key}")
    private String apiKey;

    public void send(String from, String to, String subject, String content) {
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content mailContent = new Content("text/plain", content);
        Mail mail = new Mail(fromEmail, subject, toEmail, mailContent);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if (response.getStatusCode() >= 400) {
                throw new RuntimeException("SendGrid error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email via SendGrid", e);
        }
    }
}
