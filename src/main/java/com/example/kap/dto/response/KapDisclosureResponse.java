package com.example.kap.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //DTO'da tanımlamadığımız alanları görmezden gelmesini sağlıyor.
public class KapDisclosureResponse {

    private String publishDate;
    private String disclosureClass;
    private String title;
    private String summary;
    private String companyTitle;
    private Long disclosureIndex;
    private Integer attachmentCount;
    private String mkkMemberOid;
    private String taxonomySetOid;

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDisclosureClass() {
        return disclosureClass;
    }

    public void setDisclosureClass(String disclosureClass) {
        this.disclosureClass = disclosureClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getMkkMemberOid() {
        return mkkMemberOid;
    }

    public void setMkkMemberOid(String mkkMemberOid) {
        this.mkkMemberOid = mkkMemberOid;
    }

    public Long getDisclosureIndex() {
        return disclosureIndex;
    }

    public void setDisclosureIndex(Long disclosureIndex) {
        this.disclosureIndex = disclosureIndex;
    }

    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(Integer attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getTaxonomySetOid() {
        return taxonomySetOid;
    }

    public void setTaxonomySetOid(String taxonomySetOid) {
        this.taxonomySetOid = taxonomySetOid;
    }
}


//publishDate       → Bildirim Tarihi
//disclosureClass   → Bildirim Tipi
//title             → Bildirim Konusu
//summary           → Özet Bilgi
//companyTitle      → İlgili Şirket
//disclosureIndex   → Bildirim ID
//attachmentCount   → Ek Sayısı
//mkkMemberOid      → KAP Şirket Profil ID
// taxonomySetOid → Bildirimin bağlı olduğu KAP bildirim konusu ID'si 