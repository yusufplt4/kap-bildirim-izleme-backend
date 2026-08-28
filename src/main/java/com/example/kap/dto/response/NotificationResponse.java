package com.example.kap.dto.response;

public class NotificationResponse {

    private Long id;
    private String companyName;
    private String message;


    public NotificationResponse(
            Long id,
            String companyName,
            String message
    ){
        this.id = id;
        this.companyName = companyName;
        this.message = message;
    }

    public Long getId(){
        return id;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getMessage(){
        return message;
    }
}
