package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "username";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginUserId(HttpSession session) {
        String username = (String)session.getAttribute(USER_SESSION_KEY);
        return username;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
    public static boolean isLoginUser(String username, HttpSession session) {
        String loginUser = getLoginUserId(session);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(username);
    }
}
