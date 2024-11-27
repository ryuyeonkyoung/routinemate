package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.domain.Post;
//import model.domain.User;
import model.domain.PostTask;

public class PostDAO {
    private JDBCUtil jdbcUtil = null;
    
    public PostDAO() {
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }

    // Post와 Task 목록을 함께 저장하는 메서드
    public int addPostWithTasks(Post post) throws SQLException {
        String postSql = "INSERT INTO posts (post_id, post_title, post_date, post_author) VALUES (?, ?, ?, ?)";
        String taskSql = "INSERT INTO tasks (post_task_id, post_id, user_id, order, description, isCompleted) VALUES (?, ?, ?, ?, ?, ?)";

        Object[] postParams = { post.getPostId(), post.getPostTitle(), new java.sql.Date(post.getPostDate().getTime()), post.getPostAuthor() };
        jdbcUtil.setSqlAndParameters(postSql, postParams);  // insert문과 매개 변수 설정
        
        try {
            int result = jdbcUtil.executeUpdate();  // Post insert 실행
            
            // Task insert 실행
            for (PostTask task : post.getTasks()) {
                Object[] taskParams = { task.getTaskId(), post.getPostId(), /*task.getUserId() merge 후 수정*/0, task.getOrder(), task.getDescription(), task.isCompleted()}; //, new java.sql.Date(task.getTaskDate().getTime())마지막 필드로 삭제
                jdbcUtil.setSqlAndParameters(taskSql, taskParams);
                jdbcUtil.executeUpdate();  // 개별 Task insert 실행
            }
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();  // 리소스 반환
        }
        return 0;
    }

    // 특정 Post와 관련된 Task 목록을 포함하여 조회
    public Post getPostWithTasks(int postId) throws SQLException {
        String postSql = "SELECT * FROM posts WHERE post_id = ?";
        String taskSql = "SELECT * FROM tasks WHERE post_id = ?";

        jdbcUtil.setSqlAndParameters(postSql, new Object[]{postId});
        
        try {
            ResultSet postRs = jdbcUtil.executeQuery();
            Post post = null;

            if (postRs.next()) {
                post = new Post();
                post.setPostId(postRs.getInt("post_id"));
                post.setPostTitle(postRs.getString("post_title"));
                post.setPostDate(postRs.getDate("post_date"));
                post.setPostAuthor(postRs.getString("post_author"));
            }
            postRs.close();

            // Task 목록 조회
            if (post != null) {
                jdbcUtil.setSqlAndParameters(taskSql, new Object[]{postId});
                ResultSet taskRs = jdbcUtil.executeQuery();

                List<PostTask> tasks = new ArrayList<>();
                while (taskRs.next()) {
                    PostTask task = new PostTask();
                    task.setTaskId(taskRs.getInt("post_task_id"));
                    //task.setPostId(taskRs.getInt("post_id")); //필요성 검토
                    //task.setUserId(taskRs.getInt("user_id")); //merge후 활성화 
                    task.setOrder(taskRs.getInt("order"));
                    task.setDescription(taskRs.getString("description"));
                    task.setCompleted(taskRs.getBoolean("isCompleted"));
                    tasks.add(task);
                }
                taskRs.close();
                post.setTasks(tasks);
            }
            return post;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();  // 리소스 반환
        }
        return null;
    }

    // 특정 Post와 관련된 Task 목록을 포함하여 갱신
    public int updatePostWithTasks(Post post) throws SQLException {
        String postSql = "UPDATE posts SET post_title = ?, post_date = ?, post_author = ? WHERE post_id = ?";
        String deleteTaskSql = "DELETE FROM tasks WHERE post_id = ?";
        String insertTaskSql = "INSERT INTO tasks (post_task_id, post_id, user_id, order, description, isCompleted, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[] postParams = { post.getPostTitle(), new java.sql.Date(post.getPostDate().getTime()), post.getPostAuthor(), post.getPostId() };
        jdbcUtil.setSqlAndParameters(postSql, postParams);  // update문과 매개 변수 설정
        
        try {
            int result = jdbcUtil.executeUpdate();  // Post update 실행

            // 기존 Task 삭제
            jdbcUtil.setSqlAndParameters(deleteTaskSql, new Object[]{post.getPostId()});
            jdbcUtil.executeUpdate();

            // 새로운 Task 삽입
            for (PostTask task : post.getTasks()) {
                Object[] taskParams = { task.getTaskId(), post.getPostId(), /*task.getUserId() merge후 활성화*/ 0, task.getOrder(), task.getDescription(), task.isCompleted()};
                jdbcUtil.setSqlAndParameters(insertTaskSql, taskParams);
                jdbcUtil.executeUpdate();
            }
            return result;
            
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();  // 리소스 반환
        }
        return 0;
    }

    // 특정 Post와 관련된 모든 Task 삭제
    public int deletePostWithTasks(int postId) throws SQLException {
        String deleteTaskSql = "DELETE FROM tasks WHERE post_id = ?";
        String deletePostSql = "DELETE FROM posts WHERE post_id = ?";

        try {
            // 관련 Task 삭제
            jdbcUtil.setSqlAndParameters(deleteTaskSql, new Object[]{postId});
            jdbcUtil.executeUpdate();

            // Post 삭제
            jdbcUtil.setSqlAndParameters(deletePostSql, new Object[]{postId});
            int result = jdbcUtil.executeUpdate();
            
            return result;
            
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();  // 리소스 반환
        }
        return 0;
    }
}
