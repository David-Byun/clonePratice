package airbnb.clone.property;

import lombok.Getter;
import lombok.Setter;


//파일이 업로드가 된 이후 response 할 class 생성
@Getter
@Setter
public class FileUploadResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public FileUploadResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
