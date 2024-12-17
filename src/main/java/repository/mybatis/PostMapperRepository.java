package repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import repository.mybatis.mapper.PostMapper;
import model.domain.Post;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PostMapperRepository {
    private SqlSessionFactory sqlSessionFactory;

    public PostMapperRepository() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public int createPost(Post post) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(PostMapper.class).createPost(post);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public int updatePost(Post post) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(PostMapper.class).updatePost(post);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public int deletePost(int postId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(PostMapper.class).deletePost(postId);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public Post getPostById(int postId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(PostMapper.class).getPostById(postId);
        } finally {
            sqlSession.close();
        }
    }

    public List<Post> getPostList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(PostMapper.class).getPostList();
        } finally {
            sqlSession.close();
        }
    }
}
