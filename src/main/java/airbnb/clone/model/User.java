package airbnb.clone.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private Integer userId;

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
