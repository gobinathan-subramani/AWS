package com.test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

public class App {

	public static Object handleRequest(Request req, Context ctx) {
		//AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		Job job = null;

		if (req.getHttpMethod().equalsIgnoreCase("GET")) {
			job = mapper.load(Job.class, req.getId());
			return job;
		} else if (req.getHttpMethod().equalsIgnoreCase("POST")) {
			job = req.getJob();
			mapper.save(job);
			return job;
		}
		return null;
	}

}
