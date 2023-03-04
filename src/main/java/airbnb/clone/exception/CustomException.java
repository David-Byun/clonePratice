package airbnb.clone.exception;

public class CustomException extends RuntimeException {
    private ErrorCode code;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CustomException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CustomException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ErrorCode getErrorCode() {
        return this.code;
    }
}
