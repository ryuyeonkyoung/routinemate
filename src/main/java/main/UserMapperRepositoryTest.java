package main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.domain.User;
import repository.mybatis.UserMapperRepository;

public class UserMapperRepositoryTest { // USER CRUD 테스트용 코드
    private static UserMapperRepository userDao = new UserMapperRepository();

    public static void main(String[] args) {
        System.out.println("UserMapperRepositoryTest starts...");
        try {
            // user create
            createUser("dongduk1234", "andy", "111@naver.com", true);
            createUser("rkskekfk1111", "ann", "222@naver.com", false);
            createUser("dhifho1357", "lisa", "333@naver.com", true);
            System.out.println();

            // user update
            updateUser(1, "dongduk1111", "1번유저", "111@gmail.com", Calendar.getInstance().getTime(), false);
            System.out.println();

            // user remove
            removeUser(3);
            System.out.println();

            // user select: userId로 user정보 보여주기
            selectUser(1);
            selectUser(2);

            // user select: 전체 UserList 보여주기
            selectUserList();
            System.out.println();

            // user select: userid로 사용자 존재여부 출력
            existingUser(1); // 사용자 존재 여부 확인
            existingUser(99999999); // 존재하지 않는 사용자 확인

        } finally {
            System.out.println();
        }
    }

    public static void selectUser(int userId) {
        System.out.println("selectUser(" + userId + "): ");

        User user = userDao.selectUser(userId);
        System.out.println(user);
    }

    public static void selectUserList() {
        System.out.println("selectUserList(): ");
        List<User> users = userDao.selectUserList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void existingUser(int userId) {
        System.out.println("existingUser(" + userId + "): ");
        boolean exists = userDao.existingUser(userId);
        System.out.println(userId + (exists ? " 사용자 있음. " : " 사용자 없음."));
    }

    public static void createUser(String password, String username, String email, boolean isMorningType) {
        System.out.println("createUser - 아이디 : " + username + ", 비밀번호 : " + password);

        User user = new User();
        user.setPassword(password);
        user.setUserName(username);
        user.setEmail(email);
        Date birthDate = Calendar.getInstance().getTime(); // 임의로 오늘 날짜로 넣음. 실제로 구현할 때는 HTML 상에서 선택한 날짜(생일)가 들어가도록 해야함.
        user.setBirthDate(birthDate);

        String chtype = ""; // isMorningType이 true이면 chtype에 MORNING값이, false이면 EVENING값이 들어가도록 해야함.
        if (isMorningType)
            chtype = "MORNING";
        else
            chtype = "EVENING";

        user.setMorningPerson(chtype);

        int result = userDao.createUser(user);
        System.out.println("return: " + result);
    }

    public static void updateUser(int userId, String password, String username, String email, Date birthDate, boolean isMorningType) {
        System.out.println("updateUser(" + userId + ", ...): ");

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(username);
        user.setEmail(email);
        user.setBirthDate(birthDate);

        String chtype = ""; // isMorningType이 true이면 chtype에 MORNING값이, false이면 EVENING값이 들어가도록 해야함.
        if (isMorningType)
            chtype = "MORNING";
        else
            chtype = "EVENING";

        user.setMorningPerson(chtype);

        int result = userDao.updateUser(user);
        System.out.println("return: " + result);
    }

    public static void removeUser(int userId) {
        System.out.println("removeUser(" + userId + "): ");

        int result = userDao.removeUser(userId);
        System.out.println("return: " + result);
    }
}
