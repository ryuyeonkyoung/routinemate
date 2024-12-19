package model.service.exception;

/**
 * 할일 관련 예외의 최상위 클래스.
 */
public class TaskException extends Exception {
    private static final long serialVersionUID = 1L;

    public TaskException(String message) {
        super(message);
    }
}


