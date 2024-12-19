package model.service;

import java.sql.SQLException;
import java.util.List;

import repository.mybatis.UserMapperRepository;
import model.domain.User;
import model.service.exception.ExistingUserException;
import model.service.exception.UserNotFoundException;
import model.service.exception.PasswordMismatchException;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * Business 객체와 MapperRepository를 호출하여 비즈니스 로직 및 데이터 처리를 실행시키고
 * 그 결과를 Controller에 전달
 */
public class UserManager {
    /*
     * 싱글톤 패턴
     * - 단일 인스턴스를 유지하여 애플리케이션 전체에서 공유.
     * - private 생성자를 사용해 외부에서의 객체 생성을 차단.
     * - getInstance() 메서드를 통해 유일한 인스턴스를 반환.
     */
    private static UserManager userMan = new UserManager();
    private UserMapperRepository userMapperRepository;

    private UserManager() {
        try {
            userMapperRepository = new UserMapperRepository(); // MyBatis MapperRepository로 변경
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static UserManager getInstance() {
        return userMan;
    }
    
    public int create(User user) throws SQLException, ExistingUserException {
        if (userMapperRepository.existingUser(user.getUserId())) {
            throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
        }
        return userMapperRepository.createUser(user);
    }

    public int update(User user) throws SQLException, UserNotFoundException {
        return userMapperRepository.updateUser(user);
    }   

    public int remove(int userId) throws SQLException, UserNotFoundException {
        // 게시글 삭제
        // 댓글 삭제
        return userMapperRepository.removeUser(userId);
    }

    public User findUser(String username) throws SQLException, UserNotFoundException {
        User user = userMapperRepository.selectUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username + "는 존재하지 않는 아이디입니다.");
        }       
        return user;
    }
    public List<User> findUserList() throws SQLException {
        return userMapperRepository.selectUserList();
    }

    public boolean login(String username, String password)
        throws SQLException, UserNotFoundException, PasswordMismatchException {
        User user = findUser(username);
        
        if (user == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다.");
        } else if (!user.getPassword().equals(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    public UserMapperRepository getUserMapperRepository() {
        return this.userMapperRepository;
    }
}
