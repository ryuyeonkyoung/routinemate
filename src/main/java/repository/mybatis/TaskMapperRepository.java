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
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(TaskMapper.class).addTask(task);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        } finally {
            sqlSession.close();
        }
    }

    public int updateTask(Task task) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(TaskMapper.class).updateTask(task);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        } finally {
            sqlSession.close();
        }
    }
    
    public int deleteTask(int taskId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(TaskMapper.class).deleteTask(taskId);
            if (result > 0) {
                sqlSession.commit();
            } 
            return result;
        } finally {
            sqlSession.close();
        }
    }
    
    public Task getTaskById(int taskId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(TaskMapper.class).getTaskById(taskId);          
        } finally {
            sqlSession.close();
        }
    }
    
    public List<Task> getAllTasks() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(TaskMapper.class).getAllTasks();
        } finally {
            sqlSession.close();
        }
    }
    
    public List<Task> getTasksByUserId(int userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(TaskMapper.class).getTasksByUserId(userId);
        } finally {
            sqlSession.close();
        }
    }
    
    public Task getTaskByTaskId(int taskId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.getMapper(TaskMapper.class).getTaskByTaskId(taskId);          
        } finally {
            sqlSession.close();
        }
    }
        
}
