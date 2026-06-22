package edu.fu.services;

import edu.fu.dto.JobRequest;
import edu.fu.entities.Job;

import java.util.List;

public interface JobService {
    Job findById(Long id);

    Job createJob(JobRequest job);

    List<Job> findAllJobs();
}
