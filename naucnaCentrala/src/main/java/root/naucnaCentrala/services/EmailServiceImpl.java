package root.naucnaCentrala.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to) {
    	System.out.println("safasas");
        String text="safas";
        String subject ="safasf";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void mailPotvrdePrijaveRada(String to){
        String text="Prijavljen novi rad!";
        String subject ="Prijava rada";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void ispravkaFormataRada(String to){
        String text="Format rada mora biti ispravljen u roku od 20 minuta, u suprotnom ce biti izbrisan.";
        String subject ="Neispravan format rada";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void odbijenRad(String to){
        String text="Vas rad je odbijen, molimo prijavite novi.";
        String subject ="Rad odbijen";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void prihvacenRad(String to){
        String text="Vas rad je uspesno prihvacen i objavljen u casopisu.";
        String subject ="Rad prihvacen";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void prihvacenRadUzDoradu(String to){
        String text="Vas rad je prihvacen, uz potrebnu doradu, koju je potrebno izvrsiti u roku od 20min, u suprotnom ce biti odbijen.";
        String subject ="Rad prihvacen uz doradu";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void notifikacijaUrednikaNaucne(String to){
        String text="Potrebno je da odaberete recenzente za novi rad.";
        String subject ="Recenzija rada";
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
