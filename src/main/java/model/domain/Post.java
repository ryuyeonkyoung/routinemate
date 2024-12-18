package model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;  
	private int postId;
	private String postTitle;
	private Date postDate;
	private String userName; //여기선 author역할
	private List<PostTask> tasks;//내용 부분에 들어갈 task목록
	private User user;

	public Post() {}

	// 모든 필드를 포함하는 생성자
	public Post(int postId, String postTitle, Date postDate, String userName, List<PostTask> tasks) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.userName = userName;
		this.tasks = tasks;
	}

	// Getter & Setter
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PostTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<PostTask> tasks) {
		this.tasks = tasks;
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

	@Override
	public String toString() {
		return "Post [userId=" + userId + ", postId=" + postId + ", postTitle=" + postTitle + ", postDate=" + postDate
				+ ", userName=" + userName + ", tasks=" + tasks + "]";
	}	
}
