package com.example.kap.controller;

import com.example.kap.constant.PathConstant;
import com.example.kap.dto.KapMemberResponse;
import com.example.kap.dto.request.HimFilterRequest;
import com.example.kap.dto.response.HimFilterResponse;
import com.example.kap.dto.response.KapDisclosureResponse;
import com.example.kap.service.HimService;
import com.example.kap.dto.response.CompanyDisclosureTopicResponse;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;



// HTTP isteklerini karşılar ve ilgili işlemleri service katmanına yönlendirir.
@RestController
@RequestMapping(PathConstant.HIM_BASE_PATH)
public class HimController {

    private final HimService himService; // Controller doğrudan implementation'a değil service interface'ine bağımlıdır.

    public HimController(HimService himService) {
        this.himService = himService; // Constructor Injection ile bağımlılığı Spring sağlar.
    }

    @GetMapping(PathConstant.HIM_KAP_MEMBERS)
    public List<KapMemberResponse> getKapMembers(
            @PathVariable String companyType
    ) {
        return himService.getKapMembers(companyType);
    }

    @GetMapping(PathConstant.HIM_DISCLOSURES)
    public List<KapDisclosureResponse> getDisclosuresByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return himService.getDisclosuresByDateRange(
                startDate,
                endDate
        );
    }

    @PostMapping(PathConstant.HIM_FILTERS)
    public HimFilterResponse saveFilter(
            @RequestBody HimFilterRequest request
    ) {
        return himService.saveFilter(request); // Client'tan gelen filtre DTO'sunu business logic için service katmanına gönderir.
    }

    @GetMapping(PathConstant.HIM_FILTERS)
    public List<HimFilterResponse> getFilters() {
        return himService.getFilters(); // Entity yerine client'ın ihtiyacı olan response DTO listesini döndürür.
    }

    @GetMapping(PathConstant.HIM_COMPANY_DISCLOSURE_TOPICS)
    public List<CompanyDisclosureTopicResponse> getCompanyDisclosureTopics(
            @PathVariable String disclosureType
    ) {
        return himService.getCompanyDisclosureTopics(disclosureType);
    }

    @DeleteMapping(PathConstant.HIM_FILTER_BY_ID)
    public ResponseEntity<Void> deleteFilter(
            @PathVariable Long id
    ) {
        himService.deleteFilter(id);

        return ResponseEntity.noContent().build(); // Başarılı DELETE işleminde response body olmadığı için 204 döner.
    }
}