package repository.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import repository.mybatis.mapper.UserMapper;
import model.domain.User;

public class UserMapperRepository {
	private final String namespace = "repository.mybatis.mapper.UserMapper"; //추가한 코드
	private SqlSessionFactory sqlSessionFactory;
	
	public UserMapperRepository() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public User selectUser(int userId) {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(UserMapper.class).selectUser(userId);            
        }
	}
	
	//username으로 찾기
	public User selectUserByUsername(String username) { 
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(UserMapper.class).selectUserByUsername(username); 
        } 
   }
	public List<User> selectUserList() {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(UserMapper.class).selectUserList();
        }
	}
	
	public boolean existingUser(int userId) {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int count = sqlSession.getMapper(UserMapper.class).existingUser(userId);
            return count > 0; // 사용자가 존재하면 true 반환
        }
	}
	/*
	public List<User> findCommentByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(UserMapper.class).selectUserByCondition(condition);			
		} finally {
			sqlSession.close();
		}
	}*/
	public User selectUserWithTasks(int userId) {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(namespace + ".selectUserWithTasks", userId);
        }
	}
	
	public List<User> selectUserListWithTasks() {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList(namespace + ".selectUserListWithTasks");
        }
	}
	
	public int createUser(User user) {
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int result = sqlSession.getMapper(UserMapper.class).createUser(user);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        }
	}

	public int updateUser(User user) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			int result = sqlSession.getMapper(UserMapper.class).updateUser(user);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		}
	}
	
	public int removeUser(int userId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			int result = sqlSession.getMapper(UserMapper.class).removeUser(userId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		}
	}
		
}
