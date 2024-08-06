package br.com.danluan.job.service.impl;

import br.com.danluan.job.entity.Company;
import br.com.danluan.job.exception.CompanyNotFound;
import br.com.danluan.job.exception.JobNotFound;
import br.com.danluan.job.feignclient.CompanyFeignClient;
import br.com.danluan.job.model.Job;
import br.com.danluan.job.model.dto.JobDTO;
import br.com.danluan.job.repository.JobRepository;
import br.com.danluan.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyFeignClient companyFeignClient;
//
//    private ApplicationServiceImpl applicationService;
//    @Autowired
//    private CompanyServiceImpl companyServiceImpl;

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public JobDTO getJobById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return toDTO(job.get());
        } else {
            throw new JobNotFound();
        }
    }

    @Override
    public Job getJobEntityById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return job.get();
        } else {
            throw new JobNotFound();
        }
    }

    @Override
    public JobDTO save(JobDTO jobDTO) {
        Job job = toEntity(jobDTO);

        Company company = companyFeignClient.getCompany(jobDTO.getCompanyId()).getBody();
        if (company == null) {
            throw new CompanyNotFound();
        }

        jobRepository.save(job);
        return toDTO(job);
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        return null;
    }

    @Override
    public void deleteJob(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.delete(job.get());
        } else {
            throw new JobNotFound();
        }
    }

//    @Override
//    public List<ApplicationDTO> getApplicationsByJob(Integer id) {
//        List<Application> applications = jobRepository.getApplicationsByJob(id);
//
//        return applications.stream().map(applicationService::toDTO).collect(Collectors.toList());
//    }

    @Override
    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompanyId(job.getCompanyId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setPublish_date(job.getPublishDate().toString());
        jobDTO.setSalary(job.getSalary().toString());
        jobDTO.setContract_type(job.getContractType());
        return jobDTO;
    }

    @Override
    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setCompanyId(jobDTO.getCompanyId());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(Float.parseFloat(jobDTO.getSalary()));
        job.setContractType(jobDTO.getContract_type());
        return job;
    }


}
