package com.example.assig3.notification;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.example.assig3.UrlMapping.*;

@Controller
public class NotificationController {

    @MessageMapping(PATIENT)
    @SendTo(CONSULTATION + ENTITY)
    public Notification notify(Notification notification){
        return new Notification(notification.getDescription());
    }

}
