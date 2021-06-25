package com.webCars.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadService {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${s3.bucket}")
	private String bucketName;

	public URL save(MultipartFile multipartFile) throws IOException {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream input = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return save(input, contentType, fileName);
		} catch (IOException e) {
			throw new IOException("problems");
		}
	}

	private URL save(InputStream input, String contentType, String fileName) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			metadata.addUserMetadata("dateObject", LocalDateTime.now().toString());
			PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, metadata);
			amazonS3.putObject(request);
			return amazonS3.getUrl(bucketName, fileName);
		} catch (AmazonServiceException e) {
			throw new AmazonServiceException("Problems" + e.getErrorCode());
		}
	}

}
