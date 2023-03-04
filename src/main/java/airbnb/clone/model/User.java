package airbnb.clone.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private int userId;

    private String name;
    private String email;
    private String birthDay;
    private String phoneNumber;

    private LocalDateTime createAt;

    public User(String name, String email, String birthDay, String phoneNumber) {
        this.createAt = LocalDateTime.now();
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }
}
