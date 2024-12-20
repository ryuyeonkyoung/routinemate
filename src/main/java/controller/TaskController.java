package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.domain.Task;
import model.domain.User;
import model.service.TaskManager;
import model.service.UserManager;

public class TaskController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private TaskManager taskManager = TaskManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();  // 전체 URI 가져오기
        String contextPath = request.getContextPath();  // Context Path 가져오기
        String action = uri.substring(contextPath.length());  // /mypage 뒤의 경로만 추출

        log.debug("Request URI: {}", uri);
        log.debug("Context Path: {}", contextPath);
        log.debug("Action: {}", action);

        if (request.getMethod().equals("GET")) {
            // GET 요청 처리
            log.debug("GET Request: {}", action);

            switch (action) {
                case "/mypage":
                    return viewTasks(request, response);
                case "/mypage/create":
                    return "/mypage/routine_createForm.jsp";
                case "/mypage/update":
                    return showUpdateForm(request);
                case "/mypage/delete":
                    return showDeleteForm(request);
                default:
                    response.sendRedirect("/mypage/");  // 잘못된 경로 시 기본 페이지로 리다이렉션
                    return null;
            }
        } else if (request.getMethod().equals("POST")) {
            // POST 요청 처리
            log.debug("POST Request: {}", action);

            switch (action) {
                case "/mypage/create":
                    return createTask(request);
                case "/mypage/update":
                    return updateTask(request);
                case "/mypage/delete":
                    return deleteTask(request);
                default:
                    response.sendRedirect("/mypage/");
                    return null;
            }
        }

        return null; // 기본값
    }
    
    public String viewTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            // 세션에서 username 가져오기
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                // 세션이 없거나 username이 없으면 로그인 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/user/login");
                return null;
            }
            String username = (String) session.getAttribute("username");

            // 데이터베이스에서 userId 조회
            UserManager userManager = UserManager.getInstance();
            User user = userManager.findUser(username);
            int userId = user.getUserId(); // username으로 조회된 userId

            // 할 일 목록 가져오기
            TaskManager taskManager = TaskManager.getInstance();
            List<Task> tasks = taskManager.getTasksByUserId(userId);

            // JSP로 데이터 전달
            request.setAttribute("userId", userId);
            request.setAttribute("tasks", tasks);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "할 일을 불러오는 도중 문제가 발생했습니다.");
        }

        return "/mypage/routine_list.jsp"; // 할 일을 출력할 JSP로 포워드
    }

    private String showUpdateForm(HttpServletRequest request) throws Exception {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            Task task = taskManager.getTaskByTaskId(taskId);
            request.setAttribute("task", task);
            return "/mypage/routine_updateForm.jsp";
        } catch (Exception e) {
            log.error("routine.updateForm.jsp에서 오류발생 : ", e);
            return "redirect:/error";
        }
    }

    private String showDeleteForm(HttpServletRequest request) throws Exception {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            Task task = taskManager.getTaskByTaskId(taskId);
            request.setAttribute("task", task);
            return "/mypage/routine_deleteForm.jsp";
        } catch (Exception e) {
            log.error("routine.deleteForm.jsp에서 오류발생", e);
            return "redirect:/error";
        }
    }

    private String createTask(HttpServletRequest request) throws Exception {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            Task task = new Task();
            task.setUserId(userId);
            task.setDescription(request.getParameter("description"));
            task.setOrder(Integer.parseInt(request.getParameter("order")));
            task.setCompleted(Boolean.parseBoolean(request.getParameter("isCompleted")));

            taskManager.addTask(task);
            return "redirect:/mypage/view?userId=" + userId;
        } catch (Exception e) {
            log.error("Task 생성 실패", e);
            request.setAttribute("task", new Task());
            return "/mypage/routine_createForm.jsp";
        }
    }

    private String updateTask(HttpServletRequest request) throws Exception {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            Task task = new Task();
            task.setTaskId(Integer.parseInt(request.getParameter("taskId")));
            task.setDescription(request.getParameter("description"));
            task.setOrder(Integer.parseInt(request.getParameter("order")));
            task.setCompleted(Boolean.parseBoolean(request.getParameter("isCompleted")));

            taskManager.updateTask(task);
            return "redirect:/mypage/view?userId=" + userId;
        } catch (Exception e) {
            log.error("Task 업데이트 실패", e);
            request.setAttribute("task", new Task());
            return "/mypage/routine_updateForm.jsp";
        }
    }

    private String deleteTask(HttpServletRequest request) throws Exception {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            taskManager.removeTask(taskId);
            return "redirect:/mypage/view?userId=" + userId;
        } catch (Exception e) {
            log.error("Task 삭제 실패", e);
            return "redirect:/error";
        }
    }
}
