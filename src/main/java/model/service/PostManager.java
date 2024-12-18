package model.service;

import java.util.List;
import model.domain.Post;
import model.service.exception.InvalidPostException;
import model.service.exception.PostDatabaseException;
import model.service.exception.PostNotFoundException;
import repository.mybatis.PostMapperRepository;

public class PostManager {
    private static PostManager postManager = new PostManager();
    private PostMapperRepository postRepository;

    private PostManager() {
        postRepository = new PostMapperRepository();
    }

    public static PostManager getInstance() {
        return postManager;
    }

    public int createPost(Post post) throws InvalidPostException, PostDatabaseException {
        if (post == null || post.getPostTitle() == null || post.getUserName() == null) {
            throw new InvalidPostException("Post 객체의 필수 값이 누락되었습니다.");
        }

        try {
            return postRepository.createPost(post);
        } catch (Exception e) {
            throw new PostDatabaseException("게시물 생성 중 데이터베이스 오류 발생", e);
        }
    }

    public int updatePost(Post post) throws InvalidPostException, PostNotFoundException, PostDatabaseException {
        if (post == null || post.getPostId() <= 0) {
            throw new InvalidPostException("수정할 Post 객체의 ID가 유효하지 않습니다.");
        }

        try {
            if (postRepository.getPostById(post.getPostId()) == null) {
                throw new PostNotFoundException("ID가 " + post.getPostId() + "인 게시물을 찾을 수 없습니다.");
            }
            return postRepository.updatePost(post);
        } catch (Exception e) {
            throw new PostDatabaseException("게시물 수정 중 데이터베이스 오류 발생", e);
        }
    }

    public int deletePost(int postId) throws InvalidPostException, PostNotFoundException, PostDatabaseException {
        if (postId <= 0) {
            throw new InvalidPostException("삭제할 Post ID가 유효하지 않습니다.");
        }

        try {
            if (postRepository.getPostById(postId) == null) {
                throw new PostNotFoundException("ID가 " + postId + "인 게시물을 찾을 수 없습니다.");
            }
            return postRepository.deletePost(postId);
        } catch (Exception e) {
            throw new PostDatabaseException("게시물 삭제 중 데이터베이스 오류 발생", e);
        }
    }

    public List<Post> getPostList() throws PostDatabaseException {
        try {
            return postRepository.getPostList();
        } catch (Exception e) {
            throw new PostDatabaseException("게시물 목록 조회 중 데이터베이스 오류 발생", e);
        }
    }

    public Post getPostById(int postId) throws InvalidPostException, PostNotFoundException, PostDatabaseException {
        if (postId <= 0) {
            throw new InvalidPostException("조회할 Post ID가 유효하지 않습니다.");
        }

        try {
            Post post = postRepository.getPostById(postId);
            if (post == null) {
                throw new PostNotFoundException("ID가 " + postId + "인 게시물을 찾을 수 없습니다.");
            }
            return post;
        } catch (Exception e) {
            throw new PostDatabaseException("게시물 상세 조회 중 데이터베이스 오류 발생", e);
        }
    }
}
