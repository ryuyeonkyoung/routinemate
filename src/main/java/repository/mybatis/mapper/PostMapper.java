package repository.mybatis.mapper;

import model.domain.Post;
import java.util.List;

public interface PostMapper {
    
    int createPost(Post post);
    
    int updatePost(Post post);
    
    int deletePost(int postId);
    
    Post getPostById(int postId);
    
    List<Post> getPostList();
}
