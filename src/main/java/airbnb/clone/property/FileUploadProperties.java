package airbnb.clone.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
@NoArgsConstructor
@Getter
@Setter
public class FileUploadProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public FileUploadProperties(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
