package com.example.kap.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "him_filters")
public class HimFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filterName;

    private boolean active;

    private String companyType;

    @ElementCollection
    @CollectionTable(
            name = "him_filter_companies",
            joinColumns = @JoinColumn(name = "filter_id")
    )
    @Column(name = "mkk_member_oid")
    private List<String> companyOids = new ArrayList<>(); // Seçilen şirketlerin mkkMemberOid değerlerini ayrı tabloda tutar.

    @ElementCollection
    @CollectionTable(
            name = "him_filter_topics",
            joinColumns = @JoinColumn(name = "filter_id")
    )
    private List<HimFilterTopic> topics;

    private boolean consolidatedOnly;

    public HimFilter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<HimFilterTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<HimFilterTopic> topics) {
        this.topics = topics;
    }
}
