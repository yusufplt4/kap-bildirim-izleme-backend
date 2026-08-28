package com.example.kap.service;

import com.example.kap.dto.response.KapDisclosureResponse;

import java.util.List;

public interface KapService {

    List<KapDisclosureResponse> getTodayDisclosures();
}