package bootcamp.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    
    @Autowired
    private JavaMailSender sender;
 
    public void sendMail(String to, String subject, String messages){
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messages);
        message.setFrom("noreply.cloudsfitness@clouds.co.id");
        sender.send(message);
    }
}
