package model.domain;

import java.util.Date;
import java.util.List;

public class Post {

	private int postId;
	private String postTitle;
	private Date postDate;
	private String postAuthor;
	private List<PostTask> tasks;//내용 부분에 들어갈 task목록
	// private User user;///양방향 관계 나중에 활성화

	public Post() {
	}

	// 모든 필드를 포함하는 생성자
	public Post(int postId, String postTitle, Date postDate, String postAuthor, List<PostTask> tasks) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postAuthor = postAuthor;
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

	public String getPostAuthor() {
		return postAuthor;
	}

	public void setPostAuthor(String postAuthor) {
		this.postAuthor = postAuthor;
	}

	public List<PostTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<PostTask> tasks) {
		this.tasks = tasks;
	}

	/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

}
