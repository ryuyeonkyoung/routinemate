package model.service.exception;

/**
 * 게시물 데이터베이스 작업 중 실패한 경우 발생하는 예외.
 */
public class PostDatabaseException extends PostException {
	private static final long serialVersionUID = 1L;

	public PostDatabaseException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
