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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(UserMapper.class).selectUser(userId);			
		} finally {
			sqlSession.close();
		}
	}
	
	//username으로 찾기
	public User selectUserByUsername(String username) { 
        SqlSession sqlSession = sqlSessionFactory.openSession(); 
        try { 
            return sqlSession.getMapper(UserMapper.class).selectUserByUsername(username); 
        }finally { 
            sqlSession.close(); 
        } 
   }
	public List<User> selectUserList() {
	    SqlSession sqlSession = sqlSessionFactory.openSession();
	    try {
	        return sqlSession.getMapper(UserMapper.class).selectUserList();
	    } finally {
	        sqlSession.close();
	    }
	}
	
	public boolean existingUser(int userId) {
	    SqlSession sqlSession = sqlSessionFactory.openSession();
	    try {
	        int count = sqlSession.getMapper(UserMapper.class).existingUser(userId);
	        return count > 0; // 사용자가 존재하면 true 반환
	    } finally {
	        sqlSession.close();
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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (User)sqlSession.selectOne(
					namespace + ".selectUserWithTasks", userId);
		} finally {
			sqlSession.close();
		}
	}//추가한 코드
	
	public List<User> selectUserListWithTasks() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					namespace + ".selectUserListWithTasks");
		} finally {
			sqlSession.close();
		}
	}//추가한 코드
	
	public int createUser(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).createUser(user);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int updateUser(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).updateUser(user);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public int removeUser(int userId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(UserMapper.class).removeUser(userId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
		
}
