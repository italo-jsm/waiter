package com.italo.waiter.service.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class S3Service {
    private final String bucket = "bucket";
    private final String keyName = "fileObjKeyName";
    public void putObject(){
        AmazonS3 client = generateClient();
        PutObjectRequest request = new PutObjectRequest(bucket, keyName, new File("Teste.txt"));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("plain/text");
        metadata.addUserMetadata("title", "someTitle");
        request.setMetadata(metadata);
        client.putObject(request);
    }

    private AmazonS3 generateClient() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(generateCredentials()))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    private AWSCredentials generateCredentials() {
        return new BasicAWSCredentials(
                "accesskey",
                "secretkey"
        );
    }
}
