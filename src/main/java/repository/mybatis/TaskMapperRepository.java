package repository.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import repository.mybatis.mapper.TaskMapper;
import model.domain.Task;

public class TaskMapperRepository {
    private SqlSessionFactory sqlSessionFactory;
    
    public TaskMapperRepository() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    
    public int addTask(Task task) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int result = sqlSession.getMapper(TaskMapper.class).addTask(task);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        }
    }

    public int updateTask(Task task) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int result = sqlSession.getMapper(TaskMapper.class).updateTask(task);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        }
    }
    
    public int deleteTask(int taskId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int result = sqlSession.getMapper(TaskMapper.class).deleteTask(taskId);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        }
    }
    
    public Task getTaskById(int taskId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskMapper.class).getTaskByTaskId(taskId);          
        }
    }
    
    public List<Task> getAllTasks() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskMapper.class).getAllTasks();
        }
    }
    
    public List<Task> getTasksByUserId(int userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskMapper.class).getTasksByUserId(userId);
        }
    }
    
    public Task getTaskByTaskId(int taskId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskMapper.class).getTaskByTaskId(taskId);          
        }
    }
        
}
