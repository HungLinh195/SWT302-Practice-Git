package edu.fu.dao;

import edu.fu.entities.Department;
import edu.fu.entities.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

class JobDaoImplTest {
    private static JobDao jobDao;

    @BeforeAll
    public static void setUp() {
        jobDao = new JobDaoImpl();
    }

    @Test
    void findById() {
    }

    @Test
    void createJob1() {
        // Input
        Job job = new Job();

        job.setTitle("Senior Java Backend Developer");

        job.setDescription("""
                We are looking for a Senior Java Backend Developer to build scalable microservices 
                for an EdTech platform. The candidate will work with Spring Boot, PostgreSQL, Docker, 
                and Kubernetes in an Agile environment.
                Responsibilities include designing REST APIs, implementing authentication & authorization, 
                optimizing database performance, and collaborating with frontend/mobile teams.
                """);

        job.setLocation("Ha Noi, Viet Nam");

        job.setMinSalary(25000000.00);
        job.setMaxSalary(45000000.00);

        job.setStatus("OPEN");

        job.setUtmSource("linkedin");
        job.setUtmMedium("social-media");

        job.setDeadline(Instant.parse("2026-06-30T23:59:59Z"));
        job.setPublishAt(Instant.parse("2026-05-21T08:00:00Z"));

        Department department = new Department();
        department.setId(1L); // Detached

        job.setDepartment(department);

        // Get actual result
        Job actualResult = jobDao.createJob(job);

        // Compare với expected result
        Assertions.assertEquals(job.getTitle(), actualResult.getTitle());

    }

    @Test
    void createJob2() {
    }

    @Test
    void findAllJobs() {

        List<Job> jobs = jobDao.findAllJobs();

        jobs.forEach(job -> {
            System.out.println(job.getTitle() + "\t" + job.getDepartment().getDepartmentName());
        });

        Assertions.assertEquals(1, jobs.size());
    }
}