package model.dao;

import java.sql.*;
import java.util.*;

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
                   + "morningRoutineForEveningType, afternoonRoutineForEveningType, eveningRoutineForEveningType, chronotype "
                   + "FROM RECOMMENDATION WHERE userId = ?";

        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId}); // query문과 매개 변수 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery();    // query 실행
            if (rs.next()) {
                Recommendation recommendation = new Recommendation();
                recommendation.setUserId(rs.getInt("userId"));

                // 사용자의 chronotype을 가져옵니다.
                String chronotype = rs.getString("chronotype");

                // chronotype에 맞는 루틴을 선택합니다.
                if ("morning".equals(chronotype)) {
                    // morning chronotype인 경우, morning 루틴에 맞는 데이터 설정
                    recommendation.setMorningRoutineForMorningType(parseTaskIds(rs.getString("morningRoutineForMorningType")));
                    recommendation.setAfternoonRoutineForMorningType(parseTaskIds(rs.getString("afternoonRoutineForMorningType")));
                    recommendation.setEveningRoutineForMorningType(parseTaskIds(rs.getString("eveningRoutineForMorningType")));
                } else if ("evening".equals(chronotype)) {
                    // evening chronotype인 경우, evening 루틴에 맞는 데이터 설정
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
    public int createRecommendation(Recommendation recommendation) throws SQLException {
        String sql = "INSERT INTO RECOMMENDATION (userId, "
                   + "morningRoutineForMorningType, afternoonRoutineForMorningType, eveningRoutineForMorningType, "
                   + "morningRoutineForEveningType, afternoonRoutineForEveningType, eveningRoutineForEveningType, chronotype) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // chronotype에 따라 다르게 저장합니다.
        String chronotype = recommendation.getUserId() % 2 == 0 ? "morning" : "evening";  // 예시로, userId로 chronotype을 임의로 결정합니다.

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
            chronotype
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
    public int updateRecommendation(Recommendation recommendation) throws SQLException {
        String sql = "UPDATE RECOMMENDATION SET "
                   + "morningRoutineForMorningType = ?, afternoonRoutineForMorningType = ?, eveningRoutineForMorningType = ?, "
                   + "morningRoutineForEveningType = ?, afternoonRoutineForEveningType = ?, eveningRoutineForEveningType = ?, "
                   + "chronotype = ? WHERE userId = ?";

        String chronotype = recommendation.getUserId() % 2 == 0 ? "morning" : "evening";

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
            chronotype,
            recommendation.getUserId()
        };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            return jdbcUtil.executeUpdate();  // update문 실행
        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
    }
}
