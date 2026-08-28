package com.example.kap.service.impl;

import com.example.kap.dto.KapMemberResponse;
import com.example.kap.dto.request.HimFilterRequest;
import com.example.kap.dto.request.HimFilterTopicRequest;
import com.example.kap.dto.response.CompanyDisclosureTopicResponse;
import com.example.kap.dto.response.HimFilterResponse;
import com.example.kap.dto.response.HimFilterTopicResponse;
import com.example.kap.dto.response.KapDisclosureResponse;
import com.example.kap.entity.HimFilter;
import com.example.kap.entity.HimFilterTopic;
import com.example.kap.repository.HimFilterRepository;
import com.example.kap.service.HimService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class HimServiceImpl implements HimService {

    private final RestTemplate restTemplate;
    private final HimFilterRepository himFilterRepository;

    @Value("${kap.datafeed.him-base-url}")
    private String himBaseUrl; // KAP HİM servisinin base URL bilgisini application.properties üzerinden alır.

    public HimServiceImpl(
            RestTemplate restTemplate,
            HimFilterRepository himFilterRepository
    ) {
        this.restTemplate = restTemplate;
        this.himFilterRepository = himFilterRepository;
    }

    @Override
    public List<KapMemberResponse> getKapMembers(String companyType) {

        String url =
                himBaseUrl
                        + "/kapmembers/"
                        + companyType
                        + "/A"; // Şirket tipine göre KAP üyelerini getiren dış servis adresini oluşturur.

        ResponseEntity<List<KapMemberResponse>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<KapMemberResponse>>() {}
                ); // Generic List response tipinin çalışma zamanında korunmasını sağlar.

        return response.getBody() != null
                ? response.getBody()
                : Collections.emptyList();
    }

    @Override
    public List<KapDisclosureResponse> getDisclosuresByDateRange(
            String startDate,
            String endDate
    ) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    "Başlangıç tarihi bitiş tarihinden sonra olamaz."
            );
        }

        List<KapDisclosureResponse> allDisclosures = new ArrayList<>();

        LocalDate currentDate = start;

        while (!currentDate.isAfter(end)) { // Başlangıç ve bitiş tarihi dahil olacak şekilde gün gün ilerler.

            String formattedDate = currentDate.format(formatter);

            String url =
                    himBaseUrl
                            + "/disclosures/"
                            + formattedDate;

            ResponseEntity<List<KapDisclosureResponse>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<KapDisclosureResponse>>() {}
                    );

            if (response.getBody() != null) {
                allDisclosures.addAll(response.getBody()); // Her güne ait bildirimleri tek listede birleştirir.
            }

            currentDate = currentDate.plusDays(1);
        }

        return allDisclosures;
    }

    @Override
    public List<CompanyDisclosureTopicResponse> getCompanyDisclosureTopics(
            String disclosureType
    ) {

        String url =
                himBaseUrl
                        + "/company/disclosureTopic/"
                        + disclosureType; // Seçilen bildirim tipine göre konu listesinin KAP endpointini oluşturur.

        ResponseEntity<List<CompanyDisclosureTopicResponse>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<
                                List<CompanyDisclosureTopicResponse>>() {}
                );

        return response.getBody() != null
                ? response.getBody()
                : Collections.emptyList();
    }

    @Override
    public HimFilterResponse saveFilter(HimFilterRequest request) {

        if (request.getFilterName() == null
                || request.getFilterName().isBlank()) {

            throw new IllegalArgumentException(
                    "Filtre adı boş olamaz."
            );
        }

        List<String> requestedCompanyOids =
                request.getCompanyOids() == null
                        ? Collections.emptyList()
                        : request.getCompanyOids();

        if (requestedCompanyOids.isEmpty()) {
            throw new IllegalArgumentException(
                    "Filtre için en az bir şirket seçilmelidir."
            );
        }

        String filterName = request.getFilterName().trim();

        HimFilter existingFilter =
                himFilterRepository
                        .findByFilterNameIgnoreCase(filterName)
                        .orElse(null); // Aynı isimde filtre varsa yeni kayıt oluşturmak yerine mevcut filtre güncellenir.

        List<HimFilterTopic> requestedTopics =
                toTopicEntities(request.getTopics()); // Client'tan gelen topic DTO'larını DB entity yapısına dönüştürür.

        HimFilter savedFilter;

        if (existingFilter == null) {

            HimFilter newFilter = new HimFilter();

            newFilter.setFilterName(filterName);
            newFilter.setActive(request.isActive());
            newFilter.setCompanyType(request.getCompanyType());
            newFilter.setCompanyOids(requestedCompanyOids);
            newFilter.setTopics(requestedTopics);
            newFilter.setConsolidatedOnly(request.isConsolidatedOnly());

            savedFilter = himFilterRepository.save(newFilter);

        } else {

            boolean companiesChanged =
                    existingFilter.getCompanyOids().size()
                            != requestedCompanyOids.size()
                            ||
                            !existingFilter.getCompanyOids()
                                    .containsAll(requestedCompanyOids)
                            ||
                            !requestedCompanyOids
                                    .containsAll(existingFilter.getCompanyOids());
            // Şirket listelerini sıra bağımsız karşılaştırarak ekleme veya çıkarma olup olmadığını kontrol eder.

            boolean topicsChanged =
                    haveTopicsChanged(
                            existingFilter.getTopics(),
                            requestedTopics
                    );

            boolean consolidatedChanged =
                    existingFilter.isConsolidatedOnly()
                            != request.isConsolidatedOnly();

            boolean activeChanged =
                    existingFilter.isActive()
                            != request.isActive();

            boolean companyTypeChanged =
                    !Objects.equals(
                            existingFilter.getCompanyType(),
                            request.getCompanyType()
                    ); // null değerlerde hata almadan şirket tiplerini karşılaştırır.

            if (!companiesChanged
                    && !topicsChanged
                    && !consolidatedChanged
                    && !activeChanged
                    && !companyTypeChanged) {

                throw new IllegalArgumentException(
                        "Filtrede herhangi bir değişiklik yapılmadı."
                );
            }

            existingFilter.setActive(request.isActive());
            existingFilter.setCompanyType(request.getCompanyType());
            existingFilter.setCompanyOids(requestedCompanyOids);
            existingFilter.setTopics(requestedTopics);
            existingFilter.setConsolidatedOnly(request.isConsolidatedOnly());

            savedFilter = himFilterRepository.save(existingFilter);
        }

        return toResponse(savedFilter); // Entity doğrudan client'a verilmeden response DTO'ya çevrilir.
    }

    @Override
    public List<HimFilterResponse> getFilters() {

        return himFilterRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList(); // DB'den gelen entity listesini response DTO listesine dönüştürür.
    }

    @Override
    public void deleteFilter(Long id) {
        himFilterRepository.deleteById(id);
    }

    private List<HimFilterTopic> toTopicEntities(
            List<HimFilterTopicRequest> topicRequests
    ) {

        if (topicRequests == null) {
            return Collections.emptyList();
        }

        return topicRequests
                .stream()
                .map(topicRequest -> {

                    HimFilterTopic topic = new HimFilterTopic();

                    topic.setTopicOid(topicRequest.getTopicOid());
                    topic.setTopicTitle(topicRequest.getTopicTitle());
                    topic.setDisclosureType(topicRequest.getDisclosureType());
                    topic.setConsolidationMethod(
                            topicRequest.getConsolidationMethod()
                    );

                    return topic;
                })
                .toList(); // Request DTO listesini HimFilterTopic entity listesine dönüştürür.
    }

    private boolean haveTopicsChanged(
            List<HimFilterTopic> existingTopics,
            List<HimFilterTopic> newTopics
    ) {

        List<HimFilterTopic> oldTopics =
                existingTopics == null
                        ? Collections.emptyList()
                        : existingTopics;

        if (oldTopics.size() != newTopics.size()) {
            return true;
        }

        return oldTopics
                .stream()
                .anyMatch(oldTopic ->
                        newTopics
                                .stream()
                                .noneMatch(newTopic ->
                                        Objects.equals(
                                                oldTopic.getTopicOid(),
                                                newTopic.getTopicOid()
                                        )
                                                &&
                                                Objects.equals(
                                                        oldTopic.getTopicTitle(),
                                                        newTopic.getTopicTitle()
                                                )
                                                &&
                                                Objects.equals(
                                                        oldTopic.getDisclosureType(),
                                                        newTopic.getDisclosureType()
                                                )
                                                &&
                                                Objects.equals(
                                                        oldTopic.getConsolidationMethod(),
                                                        newTopic.getConsolidationMethod()
                                                )
                                )
                );
        // Aynı topic ID'si kalsa bile başlık, bildirim tipi veya konsolidasyon bilgisi değişirse güncelleme yapılmasını sağlar.
    }

    private HimFilterResponse toResponse(HimFilter filter) {

        List<HimFilterTopicResponse> topics =
                filter.getTopics() == null
                        ? Collections.emptyList()
                        : filter.getTopics()
                        .stream()
                        .map(topic ->
                             new HimFilterTopicResponse(
                                     topic.getTopicOid(),
                                     topic.getTopicTitle(),
                                     topic.getDisclosureType(),
                                     topic.getConsolidationMethod()
                             )
                        )
                        .toList(); // Topic entity'lerini response DTO'larına dönüştürür.

        return new HimFilterResponse(
                filter.getId(),
                filter.getFilterName(),
                filter.isActive(),
                filter.getCompanyType(),
                filter.getCompanyOids(),
                topics,
                filter.isConsolidatedOnly()
        ); // DB entity'sinin tamamı yerine client'ın ihtiyacı olan verileri döndürür.
    }
}