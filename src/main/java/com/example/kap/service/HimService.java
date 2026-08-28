package com.example.kap.service;

import com.example.kap.dto.KapMemberResponse;
import com.example.kap.dto.request.HimFilterRequest;
import com.example.kap.dto.response.HimFilterResponse;
import com.example.kap.dto.response.KapDisclosureResponse;
import com.example.kap.dto.response.CompanyDisclosureTopicResponse;

import java.util.List;

public interface HimService {

    List<KapMemberResponse> getKapMembers(String companyType);

    List<KapDisclosureResponse> getDisclosuresByDateRange(
            String startDate,
            String endDate
    );

    List<CompanyDisclosureTopicResponse> getCompanyDisclosureTopics(
            String disclosureType
    );

    HimFilterResponse saveFilter(HimFilterRequest request);

    List<HimFilterResponse> getFilters();

    void deleteFilter(Long id);
}