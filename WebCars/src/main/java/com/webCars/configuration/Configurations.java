package com.webCars.configuration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.webCars.repositories.UserRepository;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = UserRepository.class)
public class Configurations {

	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsId, awsKey)))
				.withRegion(Regions.SA_EAST_1).build();
	}

	@Bean
	public AmazonS3 S3() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsId, awsKey)))
				.withRegion(Regions.SA_EAST_1).build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
