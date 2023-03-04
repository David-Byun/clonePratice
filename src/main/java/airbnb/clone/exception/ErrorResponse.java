package airbnb.clone.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;

    public ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }
}
