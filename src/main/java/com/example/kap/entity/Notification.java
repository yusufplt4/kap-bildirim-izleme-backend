package com.example.kap.entity;

import jakarta.persistence.*;


//Dışarıdan kontrolsüz erişimi engellemek.




@Entity
@Table(name = "notifications")
public class Notification {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyName")
    private String companyName;

    private String message;


    public Notification(){

    }

    public Notification(String companyName,String message) {
        this.companyName = companyName;
        this.message = message;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
