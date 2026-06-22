package edu.fu.services;

import edu.fu.dao.JobDao;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Department;
import edu.fu.entities.Job;
import edu.fu.entities.JobStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {
    private JobDao jobDao;  // DI

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

//    public void setJobDao(JobDao jobDao) {
//        this.jobDao = jobDao;
//    }

    @Override
    public Job findById(Long id) {
        // Validate, rule?

        if (id == null) {
            throw new IllegalArgumentException("Job id is null");
        }

        return jobDao.findById(id);
    }

    @Override
    public Job createJob(JobRequest job) {
        // Validate
        if (job.getDeadline().compareTo(Instant.now()) < 0) {
            throw new IllegalArgumentException("Job deadline must be after the current date");
        }

        if (job.getMinSalary() >= job.getMaxSalary()) {
            throw new RuntimeException("Job max salary must be greater than min salary");
        }

        // check existing

        if (jobDao.isExisted(job.getTitle())) {
            throw new RuntimeException("Job title already exists");
        }

        return jobDao.createJob(fromDto(job));
    }


    @Override
    public List<Job> findAllJobs() {
        return List.of();
    }


    private Job fromDto(JobRequest jobRequest) {
        Job job = new Job();
        job.setDeadline(jobRequest.getDeadline());
        job.setMinSalary(jobRequest.getMinSalary());

        if (jobRequest.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(jobRequest.getDepartmentId());

            job.setDepartment(department);
        }


        job.setTitle(jobRequest.getTitle());
        job.setStatus(JobStatus.DRAFT.toString());
        job.setUtmMedium(jobRequest.getUtmMedium());
        job.setUtmSource(jobRequest.getUtmSource());

        return job;
    }
}
