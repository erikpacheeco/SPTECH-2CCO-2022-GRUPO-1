package petfinder.petfinderapi.utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public static void uploadFile(String activeProfile, String fileName, MultipartFile multipart) throws S3Exception, AwsServiceException, SdkClientException, IOException {
        if(activeProfile.equals("prod")) {
            // s3 bucket
            UploadFile.uploadFileS3(fileName.replace("\\", "/"), multipart);
        } else if(activeProfile.equals("dev")) {
            // locally
            UploadFile.uploadFileLocally(fileName, multipart);
        }
    }   

    // save file locally
    public static void uploadFileLocally(String fileName, MultipartFile multipart) {
        try {
            File path = new File(".\\src\\main\\resources\\static\\" + fileName);
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(multipart.getBytes());
            output.close();
            System.out.println("success upload to locally sotarage");;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // send file to bucket
    private static void uploadFileS3(String fileName, MultipartFile multipart) throws S3Exception, AwsServiceException, SdkClientException, IOException {

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
    }
}
