package main;

import java.util.List;
import model.domain.Task;
import repository.mybatis.TaskMapperRepository; // Correct repository for Task

public class TaskMapperRepositoryTest { // Task CRUD Test code
    private static TaskMapperRepository taskDao = new TaskMapperRepository(); // Correct repository for Task

    public static void main(String[] args) {
        System.out.println("TaskMapperRepositoryTest starts...");
        try {
            // Task create
            addTask(1, 0, 0, "공복에 물 한잔", true);
            addTask(1, 1, 1, "아침 체조", true);
            addTask(1, 2, 2, "커피 마시기", false);

            addTask(2, 3, 0, "낮잠 자기", true);
            addTask(2, 4, 1, "독서하기", true);
            addTask(2, 5, 2, "스트레칭 하기", false); 
            System.out.println();

            // Task update
            updateTask(0, 0, "공복에 차 한잔", false);
            updateTask(3, 0, "채소 섭취", false );
            System.out.println();

            // Task remove
            deleteTask(5);
            System.out.println();

            // Task select: taskId로 task정보 보여주기
            getTaskById(0);
            getTaskById(3);
            
            // Task select: 전체 taskList 보여주기
            getAllTasks();
            System.out.println();

            // Task select: 특정 유저가 가진 task 목록 조회
            getTasksByUserId(20220304); 
            getTasksByUserId(20220305); 
        } finally {
            System.out.println();
        }
    }

    // Add Task
    public static void addTask(int userId, int taskId, int order, String description, boolean isCompleted ) {
        System.out.println("addTask(" + taskId + ", ...): ");
        
        Task task = new Task();
        task.setUserId(userId);
        task.setTaskId(taskId);
        task.setOrder(order);
        task.setDescription(description);
        task.setCompleted(isCompleted); 

        int result = taskDao.addTask(task);
        System.out.println("return: " + result);
    }

    // Update Task
    public static void updateTask(int taskId, int order, String description, boolean isCompleted ) {
        System.out.println("updateTask(" + taskId + ", ...): ");
        
        Task task = new Task();
        task.setTaskId(taskId);
        task.setOrder(order);
        task.setDescription(description);
        task.setCompleted(isCompleted);

        int result = taskDao.updateTask(task);
        System.out.println("return: " + result);
    }

    // Delete Task
    public static void deleteTask(int taskId) {
        System.out.println("deleteTask(" + taskId + "): ");
        
        int result = taskDao.deleteTask(taskId);
        System.out.println("return: " + result);
    }
    
    // Get Task by ID
    public static void getTaskById(int taskId) {
        System.out.println("getTaskById(" + taskId + "): ");
        
        Task task = taskDao.getTaskById(taskId);
        System.out.println(task);     
    }  

    // Get all Tasks
    public static void getAllTasks() {
        System.out.println("getAllTasks(): ");
        List<Task> tasks = taskDao.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Get Tasks by User ID
    public static void getTasksByUserId(int userId) {
        System.out.println("getTasksByUserId(" + userId + "): ");
        List<Task> tasks = taskDao.getTasksByUserId(userId);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
