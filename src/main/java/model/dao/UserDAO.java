package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.User;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행
 * DB와 연동하여 데이터 처리 및 관리 수행
 */
public class UserDAO {
    private JDBCUtil jdbcUtil = null;
    
    public UserDAO() {          
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }
        
    /**
     * 사용자 관리 테이블에 새로운 사용자 생성.
     */
    public int create(User user) throws SQLException {
        String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?)";      
        Object[] param = new Object[] {user.getUserId(), user.getPassword(), 
                        user.getUsername(), user.getEmail(), user.getBirthDate(), user.isMorningPerson() };              
        jdbcUtil.setSqlAndParameters(sql, param);
                        
        try {               
            int result = jdbcUtil.executeUpdate(); // create문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;           
    }

    /**
     * 기존의 사용자 정보를 수정.
     */
    public int update(User user) throws SQLException {
        String sql = "UPDATE USERINFO "
                    + "SET password=?, username=?, email=?, birth_date=?, is_morning_person=? "
                    + "WHERE user_id=?";
        Object[] param = new Object[] {user.getPassword(), user.getUsername(), 
                    user.getEmail(), user.getBirthDate(), user.isMorningPerson(),
                    user.getUserId()};              
        jdbcUtil.setSqlAndParameters(sql, param);
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;
    }

    /**
     * 사용자 ID에 해당하는 사용자를 삭제.
     */
    public int remove(String userId) throws SQLException {
        String sql = "DELETE FROM USERINFO WHERE user_id=?";     
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});

        try {               
            int result = jdbcUtil.executeUpdate();  // delete 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
     * 저장하여 반환.
     */
    public User findUser(String userId) throws SQLException {
        String sql = "SELECT password, username, email, birth_date, is_morning_person "
                    + "FROM USERINFO"
                    + "WHERE user_id=? ";              
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery();     // query 실행
            if (rs.next()) {                        // 학생 정보 발견
                User user = new User(       // User 객체를 생성하여 학생 정보를 저장
                    userId,
                    rs.getString("password"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getDate("birth_date"),
                    rs.getBoolean("is_morning_person"));
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // resource 반환
        }
        return null;
    }
    
    /**
     * 전체 사용자 정보를 검색하여 List에 저장 및 반환
     */
    public List<User> findUserList() throws SQLException {
        String sql = "SELECT password, username, email, birth_date, is_morning_person " 
                   + "FROM USERINFO"
                   + "ORDER BY user_id";
        jdbcUtil.setSqlAndParameters(sql, null);
                    
        try {
            ResultSet rs = jdbcUtil.executeQuery();    
            List<User> userList = new ArrayList<User>();    // User들의 리스트 생성
            while (rs.next()) {
                // 1. user 객체 생성 및 정보 저장
                User user = new User(
                    rs.getString("user_id"),
                    rs.getString("password"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getDate("birth_date"),
                    rs.getBoolean("is_morning_person"));
                // 2. userList에 추가
                userList.add(user);
            }       
            return userList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
     */
    public boolean existingUser(String userId) throws SQLException {
        String sql = "SELECT count(*) FROM USERINFO WHERE user_id=?";      
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

}
