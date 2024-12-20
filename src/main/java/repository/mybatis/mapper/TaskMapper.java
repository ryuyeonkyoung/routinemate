package repository.mybatis.mapper;

import java.util.List;

import model.domain.Task;

public interface TaskMapper {
    
    int addTask(Task task);
    
    int updateTask(Task task);
    
    int deleteTask(int taskNo);
    
    Task getTaskById(int taskNo);
    
    List<Task> getAllTasks();
    
    List<Task> getTasksByUserId(int userNo);
    
    Task getTaskByTaskId(int taskNo);
}
