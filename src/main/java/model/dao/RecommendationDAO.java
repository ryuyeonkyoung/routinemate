package model.dao;

import java.sql.*;
import java.util.*;

import model.domain.Recommendation;
import model.domain.User;
import model.domain.Task;

public class RecommendationDAO {
    private JDBCUtil jdbcUtil = null;

    public RecommendationDAO() {
        jdbcUtil = new JDBCUtil();    // JDBCUtil 객체 생성
    }

    /**
     * 사용자 ID에 해당하는 추천 루틴을 조회합니다. 
     * chronotype에 따라 적절한 루틴을 선택합니다.
     */
    public Recommendation findRecommendationByUserId(int userId) throws SQLException {
        String sql = "SELECT userId, "
                + "morningRoutineForMorningType, afternoonRoutineForMorningType, eveningRoutineForMorningType, "
                + "morningRoutineForEveningType, afternoonRoutineForEveningType, eveningRoutineForEveningType, "
                + "isMorningPerson " 
                + "FROM RECOMMENDATION WHERE userId = ?";

     jdbcUtil.setSqlAndParameters(sql, new Object[]{userId}); // query문과 매개 변수 설정

     try {
         ResultSet rs = jdbcUtil.executeQuery();    // query 실행
         if (rs.next()) {
             Recommendation recommendation = new Recommendation();
             recommendation.setUserId(rs.getInt("userId"));

             // 사용자의 chronotype을 결정 (isMorningPerson 값 기반)
             boolean isMorningPerson = rs.getBoolean("isMorningPerson");

             if (isMorningPerson) {
                 // 아침형 사용자의 루틴 설정
                 recommendation.setMorningRoutineForMorningType(parseTaskIds(rs.getString("morningRoutineForMorningType")));
                 recommendation.setAfternoonRoutineForMorningType(parseTaskIds(rs.getString("afternoonRoutineForMorningType")));
                 recommendation.setEveningRoutineForMorningType(parseTaskIds(rs.getString("eveningRoutineForMorningType")));
             } else {
                 // 저녁형 사용자의 루틴 설정
                 recommendation.setMorningRoutineForEveningType(parseTaskIds(rs.getString("morningRoutineForEveningType")));
                 recommendation.setAfternoonRoutineForEveningType(parseTaskIds(rs.getString("afternoonRoutineForEveningType")));
                 recommendation.setEveningRoutineForEveningType(parseTaskIds(rs.getString("eveningRoutineForEveningType")));
             }

             return recommendation;
         }
     } catch (SQLException ex) {
         ex.printStackTrace();
     } finally {
         jdbcUtil.close();
     }
     return null;
    }

    /**
     * taskId 문자열을 파싱하여 List<Task>로 변환합니다.
     * @param taskIds taskId를 쉼표로 구분한 문자열
     * @return List<Task>
     */
    private List<Task> parseTaskIds(String taskIds) {
        List<Task> tasks = new ArrayList<>();
        if (taskIds != null && !taskIds.isEmpty()) {
            String[] ids = taskIds.split(",");
            for (String id : ids) {
                Task task = new Task();
                task.setTaskId(Integer.parseInt(id));
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * 추천 루틴을 생성합니다. 
     * chronotype에 맞는 루틴을 저장합니다.

     */
    public int createRecommendation(User user, Recommendation recommendation) throws Exception {
        String sql = "INSERT INTO RECOMMENDATION (userId, "
                   + "morningRoutineForMorningType, afternoonRoutineForMorningType, eveningRoutineForMorningType, "
                   + "morningRoutineForEveningType, afternoonRoutineForEveningType, eveningRoutineForEveningType, isMorningPerson) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // chronotype에 따라 다르게 저장합니다.
        boolean isMorningPerson = user.isMorningPerson();

        String morningRoutineForMorningType = taskListToString(recommendation.getMorningRoutineForMorningType());
        String afternoonRoutineForMorningType = taskListToString(recommendation.getAfternoonRoutineForMorningType());
        String eveningRoutineForMorningType = taskListToString(recommendation.getEveningRoutineForMorningType());
        
        String morningRoutineForEveningType = taskListToString(recommendation.getMorningRoutineForEveningType());
        String afternoonRoutineForEveningType = taskListToString(recommendation.getAfternoonRoutineForEveningType());
        String eveningRoutineForEveningType = taskListToString(recommendation.getEveningRoutineForEveningType());

        Object[] param = new Object[]{
            recommendation.getUserId(),
            morningRoutineForMorningType,
            afternoonRoutineForMorningType,
            eveningRoutineForMorningType,
            morningRoutineForEveningType,
            afternoonRoutineForEveningType,
            eveningRoutineForEveningType,
            isMorningPerson
        };

        jdbcUtil.setSqlAndParameters(sql, param); // insert문과 매개 변수 설정

        try {
            return jdbcUtil.executeUpdate(); // insert문 실행
        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }

    /**
     * taskList를 쉼표로 구분된 문자열로 변환합니다.
     * @param tasks task 리스트
     * @return 쉼표로 구분된 taskId 문자열
     */
    private String taskListToString(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.getTaskId()).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);  // 마지막 쉼표 제거
    }

    /**
     * 추천 루틴을 업데이트합니다. 
     * chronotype에 맞게 기존 루틴을 업데이트합니다.
     */
    public int updateRecommendation(User user, Recommendation recommendation) throws SQLException {
        String sql = "UPDATE RECOMMENDATION SET "
                   + "morningRoutineForMorningType = ?, afternoonRoutineForMorningType = ?, eveningRoutineForMorningType = ?, "
                   + "morningRoutineForEveningType = ?, afternoonRoutineForEveningType = ?, eveningRoutineForEveningType = ?, "
                   + "isMorningPerson = ? WHERE userId = ?";

        boolean isMorningPerson = user.isMorningPerson();

        String morningRoutineForMorningType = taskListToString(recommendation.getMorningRoutineForMorningType());
        String afternoonRoutineForMorningType = taskListToString(recommendation.getAfternoonRoutineForMorningType());
        String eveningRoutineForMorningType = taskListToString(recommendation.getEveningRoutineForMorningType());

        String morningRoutineForEveningType = taskListToString(recommendation.getMorningRoutineForEveningType());
        String afternoonRoutineForEveningType = taskListToString(recommendation.getAfternoonRoutineForEveningType());
        String eveningRoutineForEveningType = taskListToString(recommendation.getEveningRoutineForEveningType());

        Object[] param = new Object[]{
            morningRoutineForMorningType,
            afternoonRoutineForMorningType,
            eveningRoutineForMorningType,
            morningRoutineForEveningType,
            afternoonRoutineForEveningType,
            eveningRoutineForEveningType,
            isMorningPerson,
            recommendation.getUserId()
        };

        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {
            int result = jdbcUtil.executeUpdate();  // 쿼리 실행
            return result;  // 성공적으로 업데이트 된 행의 수를 반환
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            // 항상 commit 및 자원 반환
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        
        return 0;
    } 
    
}
