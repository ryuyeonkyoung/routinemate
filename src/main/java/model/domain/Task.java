package model.domain;

import java.util.Date;

public class Task {
    private int postId;           // 연관된 Post의 ID
    private int userId;           // 할당된 사용자 ID (선택적 필드)
    private int taskId;
    private int order;
    private String description;
    private Date taskDate; //오늘 날짜에 대한 건 calendar ui부분에서 출력하긴 해줘야하므로 필요할듯함
    private boolean isCompleted;
    
    // 생성자
    public Task() {
        this.taskId = 0;
        this.order = 0;
        this.description = "";
        this.taskDate = new Date();
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
    
    
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        return "Task [postId=" + postId + ", userId=" + userId + ", taskId=" + taskId + ", order=" + order
                + ", description=" + description + ", taskDate=" + taskDate + ", isCompleted=" + isCompleted + "]";
    }

    

    
}