package main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.domain.User;
import model.domain.Task;

import repository.mybatis.UserMapperRepository;
public class UserMapperRepositoryTest2 {//Task CRUD 테스트용 코드
	private static UserMapperRepository taskDao = new UserMapperRepository();

	public static void main(String[] args) {
		System.out.println("UserMapperRepositoryTest2 starts...");
		try {
			//task create
			/*addTask(20220304, 0, 0, "공복에 물 한잔", true);
			addTask(20220304, 1, 1, "아침 체조", true);
			addTask(20220304, 2, 2, "커피 마시기", false);
			
			addTask(20220305, 3, 0, "낮잠 자기", true);
			addTask(20220305, 4, 1, "독서하기", true);
			addTask(20220305, 5, 2, "스트레칭 하기", false);	
			System.out.println();*/

			//task update
			/*updateTask(0, 0, "공복에 차 한잔", false);
			updateTask(3, 0, "채소 섭취", false );
			System.out.println();*/
			
			//task remove
			/*deleteTask(5);
			  System.out.println();*/
			
			//task select: taskId로 task정보 보여주기
			/*getTaskById(0);
			getTaskById(3);*/
			
			/*//task select: 전체 taskList 보여주기
			getAllTasks();
			System.out.println();*/
			
			//task select: 특정 유저가 가진 task 목록 조회
	        /*getTasksByUserId(20220304); 
	        getTasksByUserId(20220305);*/ 
									
		} finally {
			System.out.println();
		}
	}

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

    public static void updateTask(int taskId, int order, String description, boolean isCompleted ) {
        System.out.println("updateTask(" + taskId + ", ");

        Task task = new Task();
        task.setTaskId(taskId);
        task.setOrder(order);
        task.setDescription(description);
        task.setCompleted(isCompleted); 

        int result = taskDao.updateTask(task);
        System.out.println("return: " + result);
    }

    public static void deleteTask(int taskId) {
        System.out.println("deleteTask(" + taskId + "): ");

        int result = taskDao.deleteTask(taskId);
        System.out.println("return: " + result);
    }
    
    public static void getTaskById(int taskId) {        
        System.out.println("getTaskById(" + taskId + "): ");
        
        Task task = taskDao.getTaskById(taskId);
        System.out.println(task);     
    }  
    
    public static void getAllTasks() {
        System.out.println("getAllTasks(): ");
        List<Task> tasks = taskDao.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    
    public static void getTasksByUserId(int userId) {
        System.out.println("getTasksByUserId(): ");
        List<Task> tasks = taskDao.getTasksByUserId(userId);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
