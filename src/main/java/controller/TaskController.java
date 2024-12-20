package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.domain.Task;
import model.service.TaskManager;

public class TaskController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private TaskManager taskManager = TaskManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getPathInfo();

        if (request.getMethod().equals("GET")) {
            // GET 요청 처리
            log.debug("GET Request: {}", action);

            switch (action) {
                case "/mypage":
                    return "/user/mypage.jsp";
                case "/mypage/view":
                    return viewTasks(request);
                case "/mypage/create":
                    return "/user/routine_createForm.jsp";
                case "/mypage/update":
                    return showUpdateForm(request);
                case "/mypage/delete":
                    return showDeleteForm(request);
                default:
                    response.sendRedirect("/mypage/");
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

        return null;
    }

    // 할일 상세조회
    private String viewTasks(HttpServletRequest request) throws Exception {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            List<Task> tasks = taskManager.getTasksByUserId(userId);
            request.setAttribute("tasks", tasks);
            return "/routine_list.jsp";
        } catch (Exception e) {
            log.error("routine_list.jsp에서 오류발생 : ", e);
            return "redirect:/error";
        }
    }

    private String showUpdateForm(HttpServletRequest request) throws Exception {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            Task task = taskManager.getTaskByTaskId(taskId);
            request.setAttribute("task", task);
            return "/routine_updateForm.jsp";
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
            return "/routine_deleteForm.jsp";
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
            return "/routine_createForm.jsp";
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
            return "/routine_updateForm.jsp";
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
