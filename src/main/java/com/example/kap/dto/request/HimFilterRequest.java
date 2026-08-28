package com.example.kap.dto.request;

import java.util.List;

public class HimFilterRequest {

    private String filterName;
    private boolean active;
    private String companyType;
    private List<String> companyOids;
    private List<HimFilterTopicRequest> topics;
    private boolean consolidatedOnly;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<String> getCompanyOids() {
        return companyOids;
    }

    public void setCompanyOids(List<String> companyOids) {
        this.companyOids = companyOids;
    }

    public boolean isConsolidatedOnly() {
        return consolidatedOnly;
    }

    public void setConsolidatedOnly(boolean consolidatedOnly) {
        this.consolidatedOnly = consolidatedOnly;
    }

    public List<HimFilterTopicRequest> getTopics() {
        return topics;
    }

    public void setTopics(List<HimFilterTopicRequest> topics) {
        this.topics = topics;
    }
}