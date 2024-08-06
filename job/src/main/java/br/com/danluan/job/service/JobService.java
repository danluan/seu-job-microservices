package br.com.danluan.job.service;

import br.com.danluan.job.model.Job;
import br.com.danluan.job.model.dto.ApplicationDTO;
import br.com.danluan.job.model.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJobs();
    JobDTO getJobById(Integer id);
    Job getJobEntityById(Integer id);
    JobDTO save(JobDTO jobDTO);
    JobDTO updateJob(JobDTO jobDTO, Integer id);
    void deleteJob(Integer id);
    JobDTO toDTO(Job job);
    Job toEntity(JobDTO jobDTO);
//    List<ApplicationDTO> getApplicationsByJob(Integer id);
}
