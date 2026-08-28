package com.example.kap.dto.request;

public class HimFilterTopicRequest {

    private String topicOid;
    private String topicTitle;
    private String disclosureType;
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