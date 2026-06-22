package edu.fu.dao;

import edu.fu.entities.Job;

import java.util.List;

public interface JobDao {
    Job findById(Long id);

    Job createJob(Job job);

    List<Job> findAllJobs();

    boolean isExisted(String title);
}
