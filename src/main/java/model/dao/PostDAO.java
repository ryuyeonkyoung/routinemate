package model.dao;

import model.domain.Post;
import model.domain.PostTask;

import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO {
    private JDBCUtil jdbcUtil = null;

    public PostDAO() {
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }

    /**
     * Post와 관련된 Task를 포함하여 새 Post를 생성
     */
    public int createPost(Post post) {
        String insertPostSQL = "INSERT INTO posts (postId, postTitle, postDate, postAuthor) VALUES (?, ?, ?, ?)";

        String insertTaskSQL = "INSERT INTO post_tasks (postId, taskId, taskOrder, description, isCompleted) VALUES (?, ?, ?, ?, ?)";

        try {
            // Post 삽입
            jdbcUtil.setSqlAndParameters(insertPostSQL, new Object[]{
                post.getPostId(),
                post.getPostTitle(),
                post.getPostDate(),
                post.getPostAuthor()
            });
            jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리

            // Task 삽입
            for (PostTask task : post.getTasks()) {
                jdbcUtil.setSqlAndParameters(insertTaskSQL, new Object[]{

                    post.getPostId(),
                    task.getTaskId(),
                    task.getOrder(),
                    task.getDescription(),
                    task.isCompleted()
                });
                jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리
            }

            jdbcUtil.commit();  // 트랜잭션 커밋
            return 1;  // 성공적으로 완료된 경우
        } catch (Exception e) {
            jdbcUtil.rollback();  // 예외 발생 시 트랜잭션 롤백
            e.printStackTrace();  // 예외 출력 (로그를 추가할 수도 있음)
            return 0;  // 실패를 나타내는 값
        } finally {
            jdbcUtil.close();  // 자원 반환
        }
    }
    
    /** 
     * 게시물 목록 조회
     */
    public List<Post> getPostList() {
        String selectPostSQL = "SELECT * FROM posts";
        String selectTasksSQL = "SELECT * FROM post_tasks WHERE postId = ?";
        List<Post> postList = new ArrayList<>();

        try {
            // Post 목록 조회
            jdbcUtil.setSqlAndParameters(selectPostSQL, new Object[]{});
            ResultSet postRS = jdbcUtil.executeQuery();

            while (postRS.next()) {
                int postId = postRS.getInt("postId");
                String postTitle = postRS.getString("postTitle");
                Date postDate = postRS.getDate("postDate");
                String postAuthor = postRS.getString("postAuthor");

                // Task 조회
                jdbcUtil.setSqlAndParameters(selectTasksSQL, new Object[]{postId});
                ResultSet taskRS = jdbcUtil.executeQuery();
                List<PostTask> tasks = new ArrayList<>();

                while (taskRS.next()) {
                    PostTask task = new PostTask();
                    task.setTaskId(taskRS.getInt("taskId"));
                    task.setOrder(taskRS.getInt("taskOrder"));
                    task.setDescription(taskRS.getString("description"));
                    task.setCompleted(taskRS.getBoolean("isCompleted"));
                    tasks.add(task);
                }

                // Post 객체에 Task 리스트 포함
                postList.add(new Post(postId, postTitle, postDate, postAuthor, tasks));
            }

            return postList;
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        } finally {
            jdbcUtil.close();  // 자원 반환
        }

        return null;  // 결과가 없으면 null 반환
    }

    /**
     * Post ID를 사용하여 Post와 관련된 Task를 조회
     */
    public Post getPostById(int postId) {
        String selectPostSQL = "SELECT * FROM posts WHERE postId = ?";
        String selectTasksSQL = "SELECT * FROM post_tasks WHERE postId = ?";

        try {
            // Post 조회
            jdbcUtil.setSqlAndParameters(selectPostSQL, new Object[]{postId});
            ResultSet postRS = jdbcUtil.executeQuery();

            if (postRS.next()) {
                String postTitle = postRS.getString("postTitle");
                Date postDate = postRS.getDate("postDate");
                String postAuthor = postRS.getString("postAuthor");

                // Task 조회
                jdbcUtil.setSqlAndParameters(selectTasksSQL, new Object[]{postId});
                ResultSet taskRS = jdbcUtil.executeQuery();
                List<PostTask> tasks = new ArrayList<>();

                while (taskRS.next()) {
                    PostTask task = new PostTask();
                    task.setTaskId(taskRS.getInt("taskId"));
                    task.setOrder(taskRS.getInt("taskOrder"));
                    task.setDescription(taskRS.getString("description"));
                    task.setCompleted(taskRS.getBoolean("isCompleted"));
                    tasks.add(task);
                }

                return new Post(postId, postTitle, postDate, postAuthor, tasks); // Post 반환
            }
        } catch (Exception e) {
            e.printStackTrace();  // 예외 출력
        } finally {
            jdbcUtil.close();  // 자원 반환
        }
        return null; // 결과가 없으면 null 반환
    }

    /**
     * Post와 관련된 모든 Task를 삭제
     */
    public int deletePost(int postId) {
        String deleteTasksSQL = "DELETE FROM post_tasks WHERE postId = ?";
        String deletePostSQL = "DELETE FROM posts WHERE postId = ?";

        try {
            // Task 삭제
            jdbcUtil.setSqlAndParameters(deleteTasksSQL, new Object[]{postId});
            jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리

            // Post 삭제
            jdbcUtil.setSqlAndParameters(deletePostSQL, new Object[]{postId});
            jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리

            jdbcUtil.commit();  // 트랜잭션 커밋
            return 1;
        } catch (Exception e) {
            jdbcUtil.rollback();  // 예외 발생 시 트랜잭션 롤백
            e.printStackTrace();  // 예외 출력 (로그를 추가할 수도 있음)
            return 0;  // 실패를 나타내는 값
        } finally {
            jdbcUtil.close();  // 자원 반환
        }
    }

    /**
     * Post와 Task를 수정
     */
    public int updatePost(Post post) {
        String updatePostSQL = "UPDATE posts SET postTitle = ?, postDate = ?, postAuthor = ? WHERE postId = ?";
        String deleteTasksSQL = "DELETE FROM post_tasks WHERE postId = ?";
        String insertTaskSQL = "INSERT INTO post_tasks (taskId, taskOrder, description, isCompleted, postId) VALUES (?, ?, ?, ?, ?)";

        try {
            // Post 수정
            jdbcUtil.setSqlAndParameters(updatePostSQL, new Object[]{
                post.getPostTitle(),
                post.getPostDate(),
                post.getPostAuthor(),
                post.getPostId()
            });
            jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리

            // 기존 Task 삭제
            jdbcUtil.setSqlAndParameters(deleteTasksSQL, new Object[]{post.getPostId()});
            jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리

            // 새로운 Task 삽입
            for (PostTask task : post.getTasks()) {
                jdbcUtil.setSqlAndParameters(insertTaskSQL, new Object[]{
                    task.getTaskId(),
                    task.getOrder(),
                    task.getDescription(),
                    task.isCompleted(),
                    post.getPostId()
                });
                jdbcUtil.executeUpdate();  // executeUpdate에서 발생할 수 있는 예외 처리
            }

            jdbcUtil.commit();  // 트랜잭션 커밋
            return 1;
        } catch (Exception e) {
            jdbcUtil.rollback();  // 예외 발생 시 트랜잭션 롤백
            e.printStackTrace();  // 예외 출력 (로그를 추가할 수도 있음)
            return 0;  // 실패를 나타내는 값
        } finally {
            jdbcUtil.close();  // 자원 반환
        }
    }
}
