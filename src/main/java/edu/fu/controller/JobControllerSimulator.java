package edu.fu.controller;

import edu.fu.dto.JobRequest;
import edu.fu.entities.Job;
import edu.fu.services.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class JobControllerSimulator {
    public static void main(String[] args) {
        JobRequest jobRequest = new JobRequest();

        jobRequest.setTitle("Fresher Java 2026");

        jobRequest.setDescription("""
                We are looking for a Fresher Java  Developer to build scalable microservices 
                for an EdTech platform. The candidate will work with Spring Boot, PostgreSQL, Docker, 
                and Kubernetes in an Agile environment.
                """);

        jobRequest.setLocation("Ha Noi, Viet Nam");

        jobRequest.setMinSalary(25000000.00);
        jobRequest.setMaxSalary(45000000.00);


        jobRequest.setUtmSource("linkedin");
        jobRequest.setUtmMedium("social-media");
        jobRequest.setDeadline(Instant.parse("2026-06-30T23:59:59Z"));

        // DI ; Dependency Injection
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.fu");
        JobService jobService = (JobService) context.getBean("jobService", JobService.class);
        Job result = jobService.createJob(jobRequest);
    }
}
