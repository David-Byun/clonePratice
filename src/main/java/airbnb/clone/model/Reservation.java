package airbnb.clone.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Reservation {
    private Room roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private User userReserve;
    private Room roomReserved;
    private MultipartFile photo;
}
