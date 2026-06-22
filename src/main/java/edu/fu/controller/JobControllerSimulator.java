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

        jobRequest.setTitle("Java Fresher Developer");

        jobRequest.setDescription("""
        We are looking for a Java Fresher Developer to join our development team.
        The candidate will participate in building and maintaining web applications
        using Java, Spring Boot, and SQL databases.

        Requirements:
        - Basic knowledge of Java Core and OOP
        - Understanding of Spring Boot framework
        - Familiarity with SQL databases
        - Good problem-solving skills
        - Ability to work in a team environment

        Benefits:
        - Professional training and mentorship
        - Opportunity to work on real projects
        - Career growth opportunities
        - Friendly and dynamic working environment
        """);

        jobRequest.setLocation("Ha Noi, Viet Nam");

        jobRequest.setMinSalary(8000000.00);
        jobRequest.setMaxSalary(15000000.00);

        jobRequest.setUtmSource("linkedin");
        jobRequest.setUtmMedium("social");

        jobRequest.setDeadline(
                Instant.parse("2026-12-31T23:59:59Z")
        );

        jobRequest.setLocation("Ha Noi, Viet Nam");

        jobRequest.setMinSalary(8000000.00);
        jobRequest.setMaxSalary(15000000.00);

        jobRequest.setUtmSource("linkedin");
        jobRequest.setUtmMedium("social");

        jobRequest.setDeadline(
                Instant.parse("2026-12-31T23:59:59Z")
        );

        jobRequest.setDescription("""
        We are looking for an experienced Senior Java Backend Developer
        to design and develop scalable microservices for our platform.

        Requirements:
        - 4+ years of Java development experience
        - Strong knowledge of Spring Boot and Spring Cloud
        - Experience with PostgreSQL, Docker, and Kubernetes
        - Experience with RESTful APIs and Microservices Architecture
        - Familiarity with CI/CD pipelines and cloud platforms

        Benefits:
        - Competitive salary package
        - Annual performance bonus
        - Health insurance
        - Flexible working environment
        """);

        // DI ; Dependency Injection
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.fu");
        JobService jobService = (JobService) context.getBean("jobService", JobService.class);
        Job result = jobService.createJob(jobRequest);
    }
}
