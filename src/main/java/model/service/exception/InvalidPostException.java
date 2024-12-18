package model.service.exception;

/**
 * 잘못된 게시물 요청에 대한 예외.
 */
public class InvalidPostException extends PostException {
	private static final long serialVersionUID = 1L;

	public InvalidPostException(String message) {
        super(message);
    }
}
