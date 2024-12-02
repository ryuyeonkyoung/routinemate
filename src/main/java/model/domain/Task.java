package model.domain;

import java.util.Date;

public class Task {           
    private int user_id;           // 할당된 사용자 ID (선택적 필드)
    private int task_id;
    private String description;
    private boolean isCompleted;
    
    // 생성자
    public Task() {
        this.task_id = 0;
        this.description = "";
        this.isCompleted = false;
    }

    // Getter & Setter
    public int getTaskId() {
        return task_id;
    }

    public void setTaskId(int task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String toString() {
        return "Task [user_id=" + user_id + ", task_id=" + task_id
                + ", description=" + description + ", isCompleted=" + isCompleted + "]";
    }

    

    
}