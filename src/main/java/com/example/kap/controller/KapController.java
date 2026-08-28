package com.example.kap.controller;

import com.example.kap.constant.PathConstant;
import com.example.kap.dto.response.KapDisclosureResponse;
import com.example.kap.service.KapService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// KAP bildirimleriyle ilgili HTTP isteklerini karşılar ve service katmanına yönlendirir.
@RestController
@RequestMapping(PathConstant.KAP_BASE_PATH)
public class KapController {

    private final KapService kapService;
    // Controller implementation'a değil KapService interface'ine bağımlıdır.

    public KapController(KapService kapService) {
        this.kapService = kapService; // Constructor Injection ile bağımlılığı Spring sağlar.
    }

    @GetMapping(PathConstant.KAP_DISCLOSURES)
    public List<KapDisclosureResponse> getTodayDisclosures() {

        return kapService.getTodayDisclosures();
        // Bugüne ait KAP bildirimlerini response DTO listesi olarak client'a döndürür.
    }
}