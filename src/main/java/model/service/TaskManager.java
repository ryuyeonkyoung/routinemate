package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.TaskDAO;
import model.dao.UserDAO;
import model.domain.Task;
import model.domain.User;

public class TaskManager {
    private static TaskManager routineMan = new TaskManager();
    private TaskDAO taskDAO;

    private TaskManager() {
        try {
            taskDAO = new TaskDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static TaskManager getInstance() {
        return routineMan;
    }
    
    public int addTask(Task task) throws SQLException, ExistingUserException {
        return taskDAO.addTask(task);
    }

    public int updateTask(Task task) throws SQLException, UserNotFoundException {
        return taskDAO.updateTask(task);
    }   

    public int removeTask(int taskId) throws SQLException, UserNotFoundException {
        return taskDAO.deleteTask(taskId);
    }

    public Task findTask(int taskId)
        throws SQLException, UserNotFoundException {
        Task task = taskDAO.getTaskById(taskId);
        
        if (task == null) {
            throw new UserNotFoundException(taskId + "는 존재하지 않는 할일입니다.");
        }       
        return task;
    }

    public List<Task> getAllTasks() throws SQLException {
            return taskDAO.getAllTasks();
    }
}
