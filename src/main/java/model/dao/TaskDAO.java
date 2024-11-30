package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Task;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Task 테이블에서 루틴 추가, 수정, 삭제, 검색 수행 
 */
public class TaskDAO {
	private JDBCUtil jdbcUtil = null;
	
	public TaskDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	public int addTask(Task task) throws SQLException{
	    String sql = "INSERT INTO TASK (order, description, taskDate, isCompleted) VALUES (?, ?, ?, ?)";
	    Object[] params = {
	            task.getOrder(),
	            task.getDescription(),
	            task.getTaskDate(),
	            task.isCompleted(),
	    };
	    jdbcUtil.setSqlAndParameters(sql, params);
	    try {
	        int result = jdbcUtil.executeUpdate();
	        return result;
	    } catch(Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }    
        return 0;
	}
	//taskid로 read
	public Task getTaskById(int taskId) {
	    String sql = "SELECT * FROM TASK WHERE taskId = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {taskId});  // JDBCUtil에 query문과 매개 변수 설정
	    Task task = null;
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
                task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setOrder(rs.getInt("order"));
                task.setDescription(rs.getString("description"));
                task.setTaskDate(rs.getDate("taskDate"));
                task.setCompleted(rs.getBoolean("isCompleted"));
            }
            rs.close();
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return task;
	}
	//모든 task read
	public List<Task> getAllTasks(){
	    String sql = "SELECT * FROM TASK";
	    jdbcUtil.setSqlAndParameters(sql, null);
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<Task> taskList = new ArrayList<>();
	        jdbcUtil.setSqlAndParameters(sql, null);
	        
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setOrder(rs.getInt("order"));
                task.setDescription(rs.getString("description"));
                task.setTaskDate(rs.getDate("taskDate"));
                task.setCompleted(rs.getBoolean("isCompleted"));
                taskList.add(task);
            }
            return taskList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
	    return null;
	}
	//task 수정 update
	public int updateTask(Task task) throws SQLException{
	    String sql = "UPDATE TASK SET order = ?, description = ?, taskDate = ?, isCompleted = ? WHERE taskId = ?";
	    Object[] params = {
                task.getOrder(),
                task.getDescription(),
                task.getTaskDate(),
                task.isCompleted(),
                task.getTaskId()
        };
	    jdbcUtil.setSqlAndParameters(sql, params);
	    try {
	        int result = jdbcUtil.executeUpdate();
	        return result;
	    } catch(Exception e) {
	        jdbcUtil.rollback();
	        e.printStackTrace();
	    } finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
	}
	//task 삭제 delete
	public int deleteTask(int taskId) throws SQLException{
	    String sql = "DELETE FROM TASK WHERE taskId = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {taskId});
	    
	    try {
	        int result = jdbcUtil.executeUpdate();
	        return result;
	    } catch(Exception e) {
	        jdbcUtil.rollback();
	        e.printStackTrace();
	    } finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }    
	    return 0;
	}
}
