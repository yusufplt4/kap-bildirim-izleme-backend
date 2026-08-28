package com.example.kap.constant;

public final class PathConstant {

    private PathConstant() {  // Sabit değerleri tutan bu sınıftan nesne oluşturulmasını engeller.

    }

    public static final String API = "/api";

    // AUTH
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_REGISTER = "/register";

    // NOTIFICATION
    public static final String GREETING = API + "/greeting";
    public static final String NOTIFICATION = API + "/notification";
    public static final String NOTIFICATIONS = API + "/notifications";
    public static final String NOTIFICATION_BY_ID = NOTIFICATIONS + "/{id}";

    // KAP
    public static final String KAP_BASE_PATH = API + "/kap";
    public static final String KAP_DISCLOSURES = "/disclosures";

    // HİM
    public static final String HIM_BASE_PATH = API + "/him";
    public static final String HIM_KAP_MEMBERS = "/kapmembers/{companyType}";
    public static final String HIM_DISCLOSURES = "/disclosures";
    public static final String HIM_FILTERS = "/filters";
    public static final String HIM_FILTER_BY_ID = "/filters/{id}";
    public static final String HIM_COMPANY_DISCLOSURE_TOPICS = "/company/disclosure-topics/{disclosureType}";
}