package petfinder.petfinderapi.utilitarios;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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
        if(activeProfile.equalsIgnoreCase("prod") || activeProfile.equalsIgnoreCase("qa")) {
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

            // redimensionando img
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(multipart.getBytes()));
            Thumbnails.of(originalImage).size(250, 600).toFile(path);

            // transformando em multipart
            FileItem fileItem = new DiskFileItemFactory().createItem("file", "img", false, multipart.getName());
            try (InputStream in = new FileInputStream(path); OutputStream out = fileItem.getOutputStream()) {
                in.transferTo(out);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid file: " + e, e);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            output.write(multipartFile.getBytes());
            output.close();
            return "http://localhost:8080/" + fileName.replace("\\", "/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // send file to bucket
    private static String uploadFileS3(String fileName, MultipartFile multipart) throws S3Exception, AwsServiceException, SdkClientException, IOException {

        File path = new File(fileName);

        // redimensionando img
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(multipart.getBytes()));
        Thumbnails.of(originalImage).size(250, 600).toFile(path);

        // transformando em multipart
        FileItem fileItem = new DiskFileItemFactory().createItem("file", "img", false, multipart.getName());
        try (InputStream in = new FileInputStream(path); OutputStream out = fileItem.getOutputStream()) {
            in.transferTo(out);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

        InputStream inputStream = multipartFile.getInputStream();

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
