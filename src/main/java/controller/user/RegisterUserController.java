package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.User;
import model.service.UserManager;
import model.service.exception.ExistingUserException;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	UserManager manager = UserManager.getInstance();

       	if (request.getMethod().equals("GET")) {	
    		// GET request
    		log.debug("RegisterForm Request");
		
			return "/user/registerForm.jsp";
	    }	

    	// POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
       	    Integer.parseInt(request.getParameter("userId")),
			request.getParameter("password"),
			request.getParameter("username"),
			request.getParameter("email"),
			request.getParameter("birthDate"),
			Boolean.parseBoolean(request.getParameter("chronoType")),
			request.getParameter("isMorningPerson")
		);
       	
        log.debug("Create User : {}", user);

		try {
			manager.create(user);
	        return "redirect:/user/mainpage";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}

