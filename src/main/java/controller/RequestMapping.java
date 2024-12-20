package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.LoginController;

import controller.user.*;
//import controller.comm.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
//    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        
        // 마이페이지 관련 경로 매핑
        mappings.put("/mypage", new TaskController());          // 할 일 목록
        mappings.put("/mypage/view", new TaskController());     // 할 일 보기
        mappings.put("/mypage/create", new TaskController());   // 할 일 추가
        mappings.put("/mypage/update", new TaskController());   // 할 일 수정
        mappings.put("/mypage/delete", new TaskController());   // 할 일 삭제
        
        // 유저 관련 경로 매핑
        mappings.put("/user/mainpage", new ForwardController("/user/mainpage.jsp"));
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}