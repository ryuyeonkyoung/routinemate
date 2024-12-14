package model.domain;

import java.io.Serializable;

public class Task implements Serializable{           
	private static final long serialVersionUID = 1L;
	
	private int userId;           // 할당된 사용자 ID (선택적 필드)
    private int taskId;
    private int order;
    private String description;
    private boolean isCompleted;
    private User user;
    
    public Task() { };
    
    // 생성자
    /*public Task() {
        this.taskId = 0;
        this.description = "";
        this.isCompleted = false;
    }*/

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
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

    public String toString() {
        return "Task [userId=" + userId + ", taskId=" + taskId
                + ", order=" + order + ", description=" + description + ", isCompleted=" + isCompleted + "]";
    } 
    
}
