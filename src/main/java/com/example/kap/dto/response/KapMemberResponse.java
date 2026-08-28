package com.example.kap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KapMemberResponse {

    private String mkkMemberOid;
    private String kapMemberTitle;
    private String stockCode;

    public String getMkkMemberOid() {
        return mkkMemberOid;
    }

    public void setMkkMemberOid(String mkkMemberOid) {
        this.mkkMemberOid = mkkMemberOid;
    }

    public String getKapMemberTitle() {
        return kapMemberTitle;
    }

    public void setKapMemberTitle(String kapMemberTitle) {
        this.kapMemberTitle = kapMemberTitle;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}