package model.domain;

import java.util.Date;
import java.util.Map;

public class DailyUserPerformance {
    private int userId;
    private Map<Date, Integer> performanceMap;

    public DailyUserPerformance(int userId, Map<Date, Integer> performanceMap) {
        this.userId = userId;
        this.performanceMap = performanceMap;
    }

    // 기본 생성자
    public DailyUserPerformance() { }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<Date, Integer> getPerformanceMap() {
        return performanceMap;
    }

    public void setPerformanceMap(Map<Date, Integer> performanceMap) {
        this.performanceMap = performanceMap;
    }
}
