package com.example.kap.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class HimFilterTopic {

    @Column(name = "topic_oid")
    private String topicOid;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "disclosure_type")
    private String disclosureType;

    @Column(name = "consolidation_method")
    private String consolidationMethod;

    public String getTopicOid() {
        return topicOid;
    }

    public void setTopicOid(String topicOid) {
        this.topicOid = topicOid;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getDisclosureType() {
        return disclosureType;
    }

    public void setDisclosureType(String disclosureType) {
        this.disclosureType = disclosureType;
    }

    public String getConsolidationMethod() {
        return consolidationMethod;
    }

    public void setConsolidationMethod(String consolidationMethod) {
        this.consolidationMethod = consolidationMethod;
    }
}