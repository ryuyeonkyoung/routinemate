package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.domain.DailyUserPerformance;

public class DailyUserPerformanceDAO {
    private JDBCUtil jdbcUtil = null;

    public DailyUserPerformanceDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    /**
     * DailyUserPerformance 테이블에 수행률 저장
     */
    public int createPerformance(DailyUserPerformance performance) throws SQLException {
        String sql = "INSERT INTO DailyUserPerformance (user_id, performance_map) VALUES (?, ?, ?)";
        Object[] param = new Object[]{
            performance.getUserId(),
            performance.getPerformanceMap()
        };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate(); // insert문 실행
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
     * 사용자 성과 데이터를 업데이트
     */
    public int updatePerformance(DailyUserPerformance performance) throws SQLException {
        String sql = "UPDATE DailyUserPerformance SET performance_map = ? WHERE user_id = ?";

        Object[] param = new Object[]{
            performance.getPerformanceMap(),
            performance.getUserId()
        };

        jdbcUtil.setSqlAndParameters(sql, param); // update문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // update문 실행
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
     * 사용자 성과 데이터를 삭제
     */
    public int deletePerformance(String userId) throws SQLException {
        String sql = "DELETE FROM DailyUserPerformance WHERE user_id = ?";

        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId}); // delete문과 매개 변수 설정

        try {
            int result = jdbcUtil.executeUpdate(); // delete문 실행
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

//    /**
//     * 사용자 ID에 해당하는 성과 데이터를 조회
//     */
//    public DailyUserPerformance findPerformanceByUserId(String userId) throws SQLException {
//        String sql = "SELECT performance_map FROM DailyUserPerformance WHERE user_id = ?";
//
//        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId}); // query문과 매개 변수 설정
//
//        try {
//            ResultSet rs = jdbcUtil.executeQuery(); // query 실행
//            if (rs.next()) {
//                // 쿼리 결과를 통해 성과 객체 생성
//                DailyUserPerformance performance = new DailyUserPerformance(
//                        userId,
//                        // performance_map 불러오기
//                        );
//                return performance; // 성과 데이터 반환
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            jdbcUtil.close();
//        }
//        return null;
//    }

}
