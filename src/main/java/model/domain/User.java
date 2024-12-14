package model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{    
    private static final long serialVersionUID = 1L;
    
    private int userId;
    private String password;
    private String username;
    private String email;
    private Date birthDate;
    private boolean chronoType; // true: 아침형, false: 저녁형
    private String isMorningPerson; // true: 아침형, false: 저녁형

    // 생성자
    public User(int userId, String password, String username, String email, Date birthDate, boolean chronoType, String isMorningPerson) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.chronoType = chronoType;
        this.isMorningPerson = isMorningPerson;
    }
    
    // birthDate를 Date 타입 말고 String으로도 받을 수 있게 함
    public User(int userId, String password, String username, String email, String birthDateString, boolean chronoType, String isMorningPerson) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.birthDate = java.sql.Date.valueOf(birthDateString); // yyyy-MM-dd 형식의 String --> java.sql.Date로 변환 
        this.chronoType = chronoType;
        this.isMorningPerson = isMorningPerson;
    }
    
    // 기본 생성자 필요함
    public User() { };

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
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
    
    public boolean getChronoType() {
        return chronoType;
    }

    public void setChronoType(boolean chronoType) {
        this.chronoType = chronoType;
    }
    
    public String getIsMorningPerson() {
        return isMorningPerson;
    }

    public void setMorningPerson(String isMorningPerson) {//true면 MORNING값 false면 EVENING값 저장
    	/*if(this.chronoType) {
    		isMorningPerson = "MORNING";
    	}else {
    		isMorningPerson = "EVENING";
    	}*/
        this.isMorningPerson = isMorningPerson;
    }       

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email
				+ ", birthDate=" + birthDate + ", isMorningPerson=" + isMorningPerson + "]";
	}
        
}
