package airbnb.clone.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode implements EnumCode{
    NO_USER("A001", "등록된 유저가 없습니다."),
    DO_ERROR("A002", "공통에러발생"),
    NO_ROOM("A003", "등록된 방이 없습니다."),

    NOT_DIRECTORY("A004", "파일을 업로드할 디렉토리를 생성하지 못했습니다"),

    NOT_ACCEPTED_MESSAGE("A005","파일명에 부적합한 문자가 포함되어 있습니다"),
    NOT_UPLOAD("A005", "파일 업로드에 실패하였습니다"),
    NOT_FOUND_FILE("A006", "파일을 찾을 수 없습니다");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
