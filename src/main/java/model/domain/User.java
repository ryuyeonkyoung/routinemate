package model.domain;

import java.util.Date;

public class User {
    private String userId;
    private String password;
    private String username;
    private String email;
    private Date birthDate;
    private boolean isMorningPerson; // true: 아침형, false: 저녁형

    // 생성자
    public User(String userId, String password, String username, String email, Date birthDate, boolean isMorningPerson) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.isMorningPerson = isMorningPerson;
    }

    // 기본 생성자
    public User() {}

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMorningPerson() {
        return isMorningPerson;
    }

    public void setMorningPerson(boolean isMorningPerson) {
        this.isMorningPerson = isMorningPerson;
    }
}
