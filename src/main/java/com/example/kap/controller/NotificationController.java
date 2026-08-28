package com.example.kap.controller;

import com.example.kap.constant.PathConstant;
import com.example.kap.dto.request.NotificationRequest;
import com.example.kap.dto.response.NotificationResponse;
import com.example.kap.service.NotificationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


// HTTP dünyasıyla Java uygulaması arasında giriş kapısı görevi görür.

@RestController // Gelen HTTP isteklerini karşılar ve Java response'larını JSON/HTTP response olarak client'a döndürür.
public class NotificationController {

    private final NotificationService notificationService;
    // Controller doğrudan NotificationServiceImpl'e değil, NotificationService interface'ine bağımlıdır.

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
        // Constructor Injection sayesinde NotificationService bağımlılığını Spring sağlar.
    }


    // Sistemin çalıştığını kontrol etmek için basit karşılama endpoint'i.
    @GetMapping(PathConstant.GREETING)
    public String greeting() {
        return "KAP Sistemine Hoş Geldiniz !";
    }


    // Veritabanındaki tüm bildirimleri response DTO listesi olarak döndürür.
    @GetMapping(PathConstant.NOTIFICATIONS)
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAllNotifications();
    }


    // Client'tan JSON olarak gelen yeni bildirim bilgisini service katmanına gönderir.
    @PostMapping(PathConstant.NOTIFICATION)
    public String saveNotification(
            @RequestBody NotificationRequest request
    ) {
        return notificationService.saveNotification(request);
    }


    // URL'deki id değerini @PathVariable ile alarak ilgili bildirimi getirir.
    @GetMapping(PathConstant.NOTIFICATION_BY_ID)
    public NotificationResponse getNotificationById(
            @PathVariable Long id
    ) {
        return notificationService.getNotificationById(id);
    }
}


// Temel request akışı:
// POSTMAN / React
//      ↓ HTTP isteği
// Tomcat Sunucusu (8080)
//      ↓
// Controller
//      ↓
// Service
//      ↓
// Repository
//      ↓
// PostgreSQL

// public → başka class'lardan erişilebilir.
// static → nesne oluşturmadan class üzerinden erişilebilir.
// final  → değişken referansı sonradan başka bir nesneye atanamaz.