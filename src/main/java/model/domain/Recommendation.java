package model.domain;

import java.util.List;

public class Recommendation {
    private int userId;
    private List<String> morningRoutineForMorningType; // 아침형 루틴
    private List<String> afternoonRoutineForMorningType; // 아침형 점심 루틴
    private List<String> eveningRoutineForMorningType; // 아침형 저녁 루틴

    private List<String> morningRoutineForEveningType; // 저녁형 아침 루틴
    private List<String> afternoonRoutineForEveningType; // 저녁형 점심 루틴
    private List<String> eveningRoutineForEveningType; // 저녁형 저녁 루틴

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getMorningRoutineForMorningType() {
        return morningRoutineForMorningType;
    }

    public void setMorningRoutineForMorningType(List<String> morningRoutineForMorningType) {
        this.morningRoutineForMorningType = morningRoutineForMorningType;
    }

    public List<String> getAfternoonRoutineForMorningType() {
        return afternoonRoutineForMorningType;
    }

    public void setAfternoonRoutineForMorningType(List<String> afternoonRoutineForMorningType) {
        this.afternoonRoutineForMorningType = afternoonRoutineForMorningType;
    }

    public List<String> getEveningRoutineForMorningType() {
        return eveningRoutineForMorningType;
    }

    public void setEveningRoutineForMorningType(List<String> eveningRoutineForMorningType) {
        this.eveningRoutineForMorningType = eveningRoutineForMorningType;
    }

    public List<String> getMorningRoutineForEveningType() {
        return morningRoutineForEveningType;
    }

    public void setMorningRoutineForEveningType(List<String> morningRoutineForEveningType) {
        this.morningRoutineForEveningType = morningRoutineForEveningType;
    }

    public List<String> getAfternoonRoutineForEveningType() {
        return afternoonRoutineForEveningType;
    }

    public void setAfternoonRoutineForEveningType(List<String> afternoonRoutineForEveningType) {
        this.afternoonRoutineForEveningType = afternoonRoutineForEveningType;
    }

    public List<String> getEveningRoutineForEveningType() {
        return eveningRoutineForEveningType;
    }

    public void setEveningRoutineForEveningType(List<String> eveningRoutineForEveningType) {
        this.eveningRoutineForEveningType = eveningRoutineForEveningType;
    }
}
