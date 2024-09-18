package com.pet_care.appointment_service.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {

    Queue<String> petQueue;

    @JmsListener(destination = "petQueue")
    public void receiveMessage(String message) {
            petQueue.add(message);
            System.out.println(petQueue.size());
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        if(!petQueue.isEmpty()){
            System.out.println(petQueue.poll());
        }
    }
}
