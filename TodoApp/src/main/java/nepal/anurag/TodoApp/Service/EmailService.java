//package nepal.anurag.TodoApp.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import nepal.anurag.TodoApp.Entity.Task;
//import nepal.anurag.TodoApp.Repository.TaskRepository;
//import nepal.anurag.TodoApp.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.List;
//
//
//@Service
//    public class EmailService {
//@Autowired
//private TaskRepository taskRepository;
//    @Autowired
//    private UserRepository userRepository;
//        @Autowired
//        private JavaMailSender mailSender;
//
//        public void sendEmail(String to, String subject, String body) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(body);
//
//            mailSender.send(message);
//
//
//        }
//
//
//    public void checkForDueDates() {
//        List<Task> tasks = taskRepository.findAll();
//        for (Task task : tasks) {
//            Period period = Period.between(task.getEnd(), LocalDate.now());
//            int daysRemaining = period.getDays();
//            if (daysRemaining == 1) {
//                String to = task.getUser().getEmail();
//                String body = "Hey only 1 Day Remains For Ur Task to Complete Mate!! Hurry up buddy!";
//                String sub = "Due Date ";
//                sendEmail(to, sub, body);
//                System.out.println(to);
//            }
//        }
//
//        }
//    }
//





package nepal.anurag.TodoApp.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import nepal.anurag.TodoApp.Entity.Task;
import nepal.anurag.TodoApp.Repository.TaskRepository;
import nepal.anurag.TodoApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Properties;
@Service
@EnableAsync
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        message.setFrom(new InternetAddress("anuragnepal99@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body, "UTF-8");

        mailSender.send(message);

    }
@Async
@Scheduled(cron = "0 0 0 * * *")
    public void checkForDueDates() {
    List<Task> tasks = taskRepository.findAll();
    for (Task task : tasks) {
        Period period = Period.between(task.getEnd(), LocalDate.now());
        int daysRemaining = period.getDays();
        if (daysRemaining == 1) {
            String to = task.getUser().getEmail();
            String body = "Hey only 1 Day Remains For Ur Task to Complete Mate!! Hurry up buddy!";
            String sub = "Due Date ";

            try {
                sendEmail(to, sub, body);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            System.out.println(to);
        }
    }
}}
