package model.domain;

import java.util.Date;

public class PostTask {
    private int taskId;
    private int order;
    private String description; 
    private boolean isCompleted;
    private Post post; 
    
    // 생성자
    public PostTask() {
        this.taskId = 0;
        this.order = 0;
        this.description = "";
        this.isCompleted = false;
    }

    // Getter & Setter
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
       
    public int getPostId() {
        return post != null ? post.getPostId() : -1; // Post가 null인 경우 기본값 반환
    }
}
