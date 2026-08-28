package com.example.kap.service;

import com.example.kap.dto.request.NotificationRequest;
import com.example.kap.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    String saveNotification(NotificationRequest request);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long id);
}