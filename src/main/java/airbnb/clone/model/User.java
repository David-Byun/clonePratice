package airbnb.clone.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@ToString
public class User {


    private Integer userId;

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;

    //숫자만 이용 가능 정규식 입력
    @NotBlank
    @Size(min=6, max=8)
    @Pattern(regexp="[0-9]+")
    private String birthDay;

    //휴대폰 번호 검증 정규식 입력
    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phoneNumber;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public String getName() {
        return name;
    }

    public User() {
    }

    public User(String name, String email, String birthDay, String phoneNumber) {
        this.createAt = LocalDateTime.now();
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }

    public User(Integer userId, String name, String email, String birthDay, String phoneNumber, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }
}
