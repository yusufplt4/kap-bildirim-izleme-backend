package com.example.kap.dto.response;

public class CompanyDisclosureTopicResponse {

    private String objId;
    private String consolidationMethod;
    private String title;

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getConsolidationMethod() {
        return consolidationMethod;
    }

    public void setConsolidationMethod(String consolidationMethod) {
        this.consolidationMethod = consolidationMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}