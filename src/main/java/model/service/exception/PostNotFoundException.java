package model.service.exception;

/**
 * 게시물을 찾을 수 없는 경우 발생하는 예외.
 */
public class PostNotFoundException extends PostException {
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
        super(message);
    }
}
