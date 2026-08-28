package com.example.kap.service.impl;

import com.example.kap.dto.request.NotificationRequest;
import com.example.kap.dto.response.NotificationResponse;
import com.example.kap.entity.Notification;
import com.example.kap.repository.NotificationRepository;
import com.example.kap.service.NotificationService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository
    ) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public String saveNotification(NotificationRequest request) {

        Notification notification = new Notification(
                request.getCompanyName(),
                request.getMessage()
        );

        notificationRepository.save(notification);

        return request.getCompanyName()
                + " şirketinden gelen mesaj veri tabanına başarıyla kaydedildi.";
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        return notificationRepository
                .findAll()
                .stream()
                .map(notification ->
                        new NotificationResponse(
                                notification.getId(),
                                notification.getCompanyName(),
                                notification.getMessage()
                        )
                )
                .toList(); // Entity listesini controller'a göndermeden response DTO listesine dönüştürür.
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {

        Notification notification =
                notificationRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Bildirim bulunamadı."
                                )
                        );

        return new NotificationResponse(
                notification.getId(),
                notification.getCompanyName(),
                notification.getMessage()
        );
    }
}