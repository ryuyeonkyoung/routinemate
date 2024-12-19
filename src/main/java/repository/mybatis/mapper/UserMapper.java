package repository.mybatis.mapper;

import java.util.List;

import model.domain.User;

public interface UserMapper {
    
    int createUser(User user);   
 
    int updateUser(User user);
    
    int removeUser(int userNo);
    
    User selectUser(int userNo);
    
    User selectUserByUsername(String username);
    
    List<User> selectUserList();
    
    int existingUser(int userNo);
}
