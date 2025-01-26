package org.example.dto;

import java.util.Date;

public class AppUserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Date registerDate;
    private String phone;

    public AppUserDTO() {
    }

    public AppUserDTO(Long id, String name, String email, String password, Date registerDate, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}