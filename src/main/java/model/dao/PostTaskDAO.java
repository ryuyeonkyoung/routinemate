package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.PostTask;

public class PostTaskDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PostTaskDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	public int addTask(PostTask task) throws SQLException{
	    String sql = "INSERT INTO TASK (taskId, order, description, isCompleted) VALUES (?, ?, ?, ?)";
	    Object[] params = {
	    		task.getTaskId(),
	            task.getOrder(),
	            task.getDescription(),
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
	public PostTask getTaskById(int taskId) {
	    String sql = "SELECT * FROM TASK WHERE taskId = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {taskId});  // JDBCUtil에 query문과 매개 변수 설정
	    PostTask task = null;
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        if (rs.next()) {
                task = new PostTask();
                task.setTaskId(rs.getInt("taskId"));
                task.setOrder(rs.getInt("order"));
                task.setDescription(rs.getString("description"));
                task.setCompleted(rs.getBoolean("isCompleted"));
            }
            rs.close();
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return task;
	}
	//모든 task read
	public List<PostTask> getAllTasks(){
	    String sql = "SELECT * FROM TASK";
	    //jdbcUtil.setSqlAndParameters(sql, null);
	    
	    try {
	        ResultSet rs = jdbcUtil.executeQuery();
	        List<PostTask> taskList = new ArrayList<>();
	        jdbcUtil.setSqlAndParameters(sql, null);
	        
            while (rs.next()) {
                PostTask task = new PostTask();
                task.setTaskId(rs.getInt("taskId"));
                task.setOrder(rs.getInt("order"));
                task.setDescription(rs.getString("description"));
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
	public int updateTask(PostTask task) throws SQLException{
	    String sql = "UPDATE TASK SET order = ?, description = ?, isCompleted = ? WHERE taskId = ?";
	    Object[] params = {
                task.getOrder(),
                task.getDescription(),
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
