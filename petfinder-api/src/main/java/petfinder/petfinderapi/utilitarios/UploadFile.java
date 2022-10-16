package petfinder.petfinderapi.utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Component
public class UploadFile {
    
    // bucket name
    private static final String BUCKET = "petfinder-bucket";

    // upload file
    public static String uploadFile(String activeProfile, String fileName, MultipartFile multipart) throws S3Exception, AwsServiceException, SdkClientException, IOException {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        fileName = fileName.replace(".", timestamp.getTime() + ".").replace(" ", "");
        if(activeProfile.equals("prod")) {
            // s3 bucket
            return "https://petfinder-bucket.s3.amazonaws.com/" + UploadFile.uploadFileS3(fileName.replace("\\", "/"), multipart);
        } else if(activeProfile.equals("dev")) {
            // locally
            return UploadFile.uploadFileLocally(fileName, multipart).replace("\\", "/");
        } else {
            return null;
        }
    }

    // save file locally
    public static String uploadFileLocally(String fileName, MultipartFile multipart) {
        try {
            File path = new File(".\\src\\main\\resources\\static\\" + fileName);
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(multipart.getBytes());
            output.close();
            return "http://localhost:8080/" + fileName.replace("\\", "/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // send file to bucket
    private static String uploadFileS3(String fileName, MultipartFile multipart) throws S3Exception, AwsServiceException, SdkClientException, IOException {

        InputStream inputStream = multipart.getInputStream();

        // building client
        S3Client client = S3Client
            .builder()
            .region(Region.US_EAST_1)
            .build();
        
        // building request
        PutObjectRequest request = PutObjectRequest
            .builder()
            .bucket(BUCKET)
            .key(fileName)
            .acl("public-read")
            .build();
        
        // sending request
        client.putObject(
            request,
            RequestBody.fromInputStream(inputStream, inputStream.available())
        );

        return fileName;
    }
}
