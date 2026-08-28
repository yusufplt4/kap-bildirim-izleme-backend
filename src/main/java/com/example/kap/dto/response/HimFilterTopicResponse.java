package com.example.kap.dto.response;

public class HimFilterTopicResponse {

    private String topicOid;
    private String topicTitle;
    private String disclosureType;
    private String consolidationMethod;

    public HimFilterTopicResponse(
            String topicOid,
            String topicTitle,
            String disclosureType,
            String consolidationMethod
    ) {
        this.topicOid = topicOid;
        this.topicTitle = topicTitle;
        this.disclosureType = disclosureType;
        this.consolidationMethod = consolidationMethod;
    }

    public String getTopicOid() {
        return topicOid;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getDisclosureType() {
        return disclosureType;
    }

    public String getConsolidationMethod() {
        return consolidationMethod;
    }
}