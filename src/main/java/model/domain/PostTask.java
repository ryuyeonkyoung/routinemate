package model.domain;

import java.io.Serializable;

public class PostTask implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private int postTaskId;
	private int postId;
    private int order;
    private String description; 
    private Post post; 
    
	public PostTask() {}

    public int getPostTaskId() {
        return postTaskId;
    }

    public void setpostTaskId(int postTaskId) {
        this.postTaskId = postTaskId;
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
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostTask [userId=" + userId + ", postTaskId=" + postTaskId + ", postId=" + postId + ", order=" + order
				+ ", description=" + description + "]";
	}
}
