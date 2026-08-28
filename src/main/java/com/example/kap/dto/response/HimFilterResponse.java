package com.example.kap.dto.response;

import java.util.List;

public class HimFilterResponse {

    private Long id;
    private String filterName;
    private boolean active;
    private String companyType;
    private List<String> companyOids;
    private List<HimFilterTopicResponse> topics;
    private boolean consolidatedOnly;

    public HimFilterResponse(
            Long id,
            String filterName,
            boolean active,
            String companyType,
            List<String> companyOids,
            List<HimFilterTopicResponse> topics,
            boolean consolidatedOnly
    ) {
        this.id = id;
        this.filterName = filterName;
        this.active = active;
        this.companyType = companyType;
        this.companyOids = companyOids;
        this.topics = topics;
        this.consolidatedOnly = consolidatedOnly;
    }

    public Long getId() {
        return id;
    }

    public String getFilterName() {
        return filterName;
    }

    public boolean isActive() {
        return active;
    }

    public String getCompanyType() {
        return companyType;
    }

    public List<String> getCompanyOids() {
        return companyOids;
    }

    public List<HimFilterTopicResponse> getTopics() {
        return topics;
    }

    public boolean isConsolidatedOnly() {
        return consolidatedOnly;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public void setCompanyOids(List<String> companyOids) {
        this.companyOids = companyOids;
    }

    public void setTopics(List<HimFilterTopicResponse> topics) {
        this.topics = topics;
    }

    public void setConsolidatedOnly(boolean consolidatedOnly) {
        this.consolidatedOnly = consolidatedOnly;
    }
}