package com.example.kap.dto.request;

public class NotificationRequest {

    private String companyName;
    private String message;

    public String getCompanyName(){
        return companyName;

    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;

    }

    public String getMessage() {
        return message;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*
        getter-setter metodlar neden kullanılır
        constructor metod nedir

        1-db'ye gönderdiğin isteği kaydet
        2-db'den kaydettiğin isteği çağır.
     */

}