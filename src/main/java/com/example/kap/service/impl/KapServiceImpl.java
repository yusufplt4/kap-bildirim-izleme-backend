package com.example.kap.service.impl;

import com.example.kap.dto.response.KapDisclosureResponse;
import com.example.kap.service.KapService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class KapServiceImpl implements KapService {

    private final RestTemplate restTemplate;

    @Value("${kap.datafeed.base-url}")
    private String baseUrl;

    public KapServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<KapDisclosureResponse> getTodayDisclosures() {

        String today = LocalDate
                .now(ZoneId.of("Europe/Istanbul"))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        // Bugünün tarihini İstanbul saat dilimine göre KAP API formatında oluşturur.

        String url = baseUrl + "/" + today;

        ResponseEntity<List<KapDisclosureResponse>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<
                                List<KapDisclosureResponse>>() {}
                );

        return response.getBody() != null
                ? response.getBody()
                : Collections.emptyList();
        // Dış servis boş body döndürürse null yerine boş liste döner.
    }
}