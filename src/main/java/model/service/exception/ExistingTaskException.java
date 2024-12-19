package model.service.exception;

public class ExistingTaskException extends Exception {
    private static final long serialVersionUID = 1L;

    public ExistingTaskException() {
        super();
    }

    public ExistingTaskException(String arg0) {
        super(arg0);
    }
}