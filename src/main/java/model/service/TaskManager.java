package model.service;

import java.sql.SQLException;
import java.util.List;

import repository.mybatis.TaskMapperRepository;
import model.domain.Task;
import model.service.exception.UserNotFoundException;

public class TaskManager {
    private static TaskManager routineMan = new TaskManager();
    private TaskMapperRepository taskMapperRepository;

    private TaskManager() {
        try {
            taskMapperRepository = new TaskMapperRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static TaskManager getInstance() {
        return routineMan;
    }
    
    public int addTask(Task task) throws SQLException {
        return taskMapperRepository.addTask(task);
    }

    public int updateTask(Task task) throws SQLException {
        return taskMapperRepository.updateTask(task);
    }   

    public int removeTask(int taskId) throws SQLException {
        return taskMapperRepository.deleteTask(taskId);
    }

    public Task findTask(int taskId) throws SQLException, UserNotFoundException {
        Task task = taskMapperRepository.getTaskById(taskId);
        
        if (task == null) {
            throw new UserNotFoundException(taskId + "는 존재하지 않는 할일입니다.");
        }       
        return task;
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskMapperRepository.getAllTasks();
    }
    
    // user-task 가 1:N 연관관계여서 필요
    public List<Task> getTasksByUserId(int userId) throws SQLException {
        return taskMapperRepository.getTasksByUserId(userId);
    }
    
    public Task getTaskByTaskId(int taskId) throws SQLException {
        return taskMapperRepository.getTaskByTaskId(taskId);
    }
}
