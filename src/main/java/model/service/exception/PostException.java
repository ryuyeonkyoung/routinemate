package model.service.exception;

/**
 * 게시물 관련 예외의 최상위 클래스.
 */
public class PostException extends Exception {
	private static final long serialVersionUID = 1L;

	public PostException(String message) {
        super(message);
    }
}


