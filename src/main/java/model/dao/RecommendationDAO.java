package model.dao;

import model.domain.Recommendation;
import model.domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendationDAO {

    private JDBCUtil jdbcUtil = null;

    public RecommendationDAO() {
        jdbcUtil = new JDBCUtil();  // JDBCUtil 객체 생성
    }

    /**
     * 추천 루틴을 생성하는 메소드 (INSERT)
     */
    public int createRecommendation(Recommendation recommendation, User user) throws SQLException {
        String sql = "SELECT * FROM RECOMMENDATION_TEMPLATES WHERE type = ?";
        String type = user.isMorningPerson() ? "morning" : "evening";  // 사용자의 아침형/저녁형 구분

        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{type});
            ResultSet rs = jdbcUtil.executeQuery();

            List<String> morningRoutine = new ArrayList<>();
            List<String> afternoonRoutine = new ArrayList<>();
            List<String> eveningRoutine = new ArrayList<>();

            // 추천 루틴을 각 목록에 추가
            while (rs.next()) {
                String routineName = rs.getString("routineName");
                String taskIds = rs.getString("taskIds");

                if ("morningRoutine".equals(routineName)) {
                    morningRoutine.add(taskIds);
                } else if ("afternoonRoutine".equals(routineName)) {
                    afternoonRoutine.add(taskIds);
                } else if ("eveningRoutine".equals(routineName)) {
                    eveningRoutine.add(taskIds);
                }
            }

            // Recommendation 객체에 루틴 세팅
            if (user.isMorningPerson()) {
                recommendation.setMorningRoutineForMorningType(morningRoutine);
                recommendation.setAfternoonRoutineForMorningType(afternoonRoutine);
                recommendation.setEveningRoutineForMorningType(eveningRoutine);
            } else {
                recommendation.setMorningRoutineForEveningType(morningRoutine);
                recommendation.setAfternoonRoutineForEveningType(afternoonRoutine);
                recommendation.setEveningRoutineForEveningType(eveningRoutine);
            }

            String insertSql = "INSERT INTO RECOMMENDATION (userId, "
                    + "morningRoutineForMorningType, afternoonRoutineForMorningType, eveningRoutineForMorningType, "
                    + "morningRoutineForEveningType, afternoonRoutineForEveningType, eveningRoutineForEveningType) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            jdbcUtil.setSqlAndParameters(insertSql, new Object[]{
                user.getUserId(),
                String.join(",", recommendation.getMorningRoutineForMorningType()),
                String.join(",", recommendation.getAfternoonRoutineForMorningType()),
                String.join(",", recommendation.getEveningRoutineForMorningType()),
                String.join(",", recommendation.getMorningRoutineForEveningType()),
                String.join(",", recommendation.getAfternoonRoutineForEveningType()),
                String.join(",", recommendation.getEveningRoutineForEveningType())
            });

            int result = jdbcUtil.executeUpdate();

            jdbcUtil.commit();

            return result;

        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } catch (Exception ex) {
            jdbcUtil.rollback(); 
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return 0;
    }

    /**
     * 추천 루틴을 사용자 ID로 조회하는 메소드 (SELECT)
     */
    public Recommendation getRecommendationByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM RECOMMENDATION WHERE userId = ?";

        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                Recommendation recommendation = new Recommendation();
                recommendation.setMorningRoutineForMorningType(Arrays.asList(rs.getString("morningRoutineForMorningType").split(",")));
                recommendation.setAfternoonRoutineForMorningType(Arrays.asList(rs.getString("afternoonRoutineForMorningType").split(",")));
                recommendation.setEveningRoutineForMorningType(Arrays.asList(rs.getString("eveningRoutineForMorningType").split(",")));
                recommendation.setMorningRoutineForEveningType(Arrays.asList(rs.getString("morningRoutineForEveningType").split(",")));
                recommendation.setAfternoonRoutineForEveningType(Arrays.asList(rs.getString("afternoonRoutineForEveningType").split(",")));
                recommendation.setEveningRoutineForEveningType(Arrays.asList(rs.getString("eveningRoutineForEveningType").split(",")));

                return recommendation;
            }
        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return null;  // 추천 루틴이 없는 경우
    }

    /**
     * 추천 루틴을 수정하는 메소드 (UPDATE)
     */
    public int updateRecommendation(Recommendation recommendation, User user) throws SQLException {
        String sql = "UPDATE RECOMMENDATION SET "
                + "morningRoutineForMorningType = ?, afternoonRoutineForMorningType = ?, eveningRoutineForMorningType = ?, "
                + "morningRoutineForEveningType = ?, afternoonRoutineForEveningType = ?, eveningRoutineForEveningType = ? "
                + "WHERE userId = ?";

        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{
                String.join(",", recommendation.getMorningRoutineForMorningType()),
                String.join(",", recommendation.getAfternoonRoutineForMorningType()),
                String.join(",", recommendation.getEveningRoutineForMorningType()),
                String.join(",", recommendation.getMorningRoutineForEveningType()),
                String.join(",", recommendation.getAfternoonRoutineForEveningType()),
                String.join(",", recommendation.getEveningRoutineForEveningType()),
                user.getUserId()
            });

            int result = jdbcUtil.executeUpdate();

            jdbcUtil.commit();

            return result;

        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } catch (Exception ex) {
            jdbcUtil.rollback(); 
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return 0;  // 실패 시
    }

    /**
     * 추천 루틴을 삭제하는 메소드 (DELETE)
     */
    public int deleteRecommendation(int userId) throws SQLException {
        String sql = "DELETE FROM RECOMMENDATION WHERE userId = ?";

        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});

            int result = jdbcUtil.executeUpdate();

            jdbcUtil.commit();

            return result;

        } catch (SQLException ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } catch (Exception ex) {
            jdbcUtil.rollback(); 
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return 0;  // 실패 시
    }
}
