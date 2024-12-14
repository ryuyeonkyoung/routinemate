package repository.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.domain.User;
import model.domain.Task;

public interface UserMapper {
	
    /*User*/
    int createUser(User user);   
 
    int updateUser(User user);
    
    int removeUser(int userNo);
    
    User selectUser(int userNo);
    
    List<User> selectUserList();
    
    int existingUser(int userNo);
    
    /*Task*/
    int addTask(Task task);
    
    int updateTask(Task task);
    
    int deleteTask(int taskNo);
    
    Task getTaskById(int taskNo);
    
    List<Task> getAllTasks();
    
    List<Task> getTasksByUserId(int userNo);
}
