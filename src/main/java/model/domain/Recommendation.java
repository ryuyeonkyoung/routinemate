package model.domain;

import java.util.List;

public class Recommendation {
    private int userId;
    private List<Task> morningRoutineForMorningType;
    private List<Task> afternoonRoutineForMorningType;
    private List<Task> eveningRoutineForMorningType;
    private List<Task> morningRoutineForEveningType;
    private List<Task> afternoonRoutineForEveningType;
    private List<Task> eveningRoutineForEveningType;

    // 생성자
    public Recommendation() {
        this.userId = 0;
        this.morningRoutineForMorningType = null;
        this.afternoonRoutineForMorningType = null;
        this.eveningRoutineForMorningType = null;
        this.morningRoutineForEveningType = null;
        this.afternoonRoutineForEveningType = null;
        this.eveningRoutineForEveningType = null;
    }

    // Getter & Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Task> getMorningRoutineForMorningType() {
        return morningRoutineForMorningType;
    }

    public void setMorningRoutineForMorningType(List<Task> morningRoutineForMorningType) {
        this.morningRoutineForMorningType = morningRoutineForMorningType;
    }

    public List<Task> getAfternoonRoutineForMorningType() {
        return afternoonRoutineForMorningType;
    }

    public void setAfternoonRoutineForMorningType(List<Task> afternoonRoutineForMorningType) {
        this.afternoonRoutineForMorningType = afternoonRoutineForMorningType;
    }

    public List<Task> getEveningRoutineForMorningType() {
        return eveningRoutineForMorningType;
    }

    public void setEveningRoutineForMorningType(List<Task> eveningRoutineForMorningType) {
        this.eveningRoutineForMorningType = eveningRoutineForMorningType;
    }

    public List<Task> getMorningRoutineForEveningType() {
        return morningRoutineForEveningType;
    }

    public void setMorningRoutineForEveningType(List<Task> morningRoutineForEveningType) {
        this.morningRoutineForEveningType = morningRoutineForEveningType;
    }

    public List<Task> getAfternoonRoutineForEveningType() {
        return afternoonRoutineForEveningType;
    }

    public void setAfternoonRoutineForEveningType(List<Task> afternoonRoutineForEveningType) {
        this.afternoonRoutineForEveningType = afternoonRoutineForEveningType;
    }

    public List<Task> getEveningRoutineForEveningType() {
        return eveningRoutineForEveningType;
    }

    public void setEveningRoutineForEveningType(List<Task> eveningRoutineForEveningType) {
        this.eveningRoutineForEveningType = eveningRoutineForEveningType;
    }
}

