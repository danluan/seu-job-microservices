package br.com.danluan.application.service.impl;

import br.com.danluan.application.enums.ApplicationStatus;
import br.com.danluan.application.exceptions.ApplicationNotFound;
import br.com.danluan.application.feignclients.JobFeignClient;
import br.com.danluan.application.feignclients.WorkerFeignClient;
import br.com.danluan.application.model.Application;
import br.com.danluan.application.model.dto.ApplicationCreateDTO;
import br.com.danluan.application.model.dto.ApplicationDTO;
import br.com.danluan.application.repository.ApplicationRepository;
import br.com.danluan.application.service.ApplicationService;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobFeignClient jobFeignClient;

    @Autowired
    private WorkerFeignClient workerFeignClient;
//
//    @Autowired
//    @Qualifier("companyServiceImpl")
//    private CompanyService companyService;
//
//    @Autowired
//    @Qualifier("serviceServiceImpl")
//    private ServiceService serviceService;

    @Override
    public List<ApplicationDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> getAllApplicationsByWorkerId(Integer workerId) {
        List<Application> applications = applicationRepository.findByWorkerId(workerId);
        return applications.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO getApplicationById(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found for ID: " + id));
        return toDTO(application);
    }

    @Override
    public ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        Application application = new Application();
        if(applicationCreateDTO.getJobId() != null) {
            application.setJobId(jobFeignClient.getJobById(applicationCreateDTO.getJobId()).getBody().getId());
            application.setWorkerId(workerFeignClient.getWorkerById(applicationCreateDTO.getWorkerId()).getBody().getId());
//        } else if (applicationCreateDTO.getServiceId() != null) {
//            application.setService(serviceService.getServiceEntityById(applicationCreateDTO.getServiceId()));
//            application.setWorker(workerService.getWorkerById(applicationCreateDTO.getWorkerId()));
        } else {
            throw new EntityNotFoundException("Job or Service not found for ID: " + applicationCreateDTO.getJobId());
        }
        application.setStatus(ApplicationStatus.P);
        return toDTO(applicationRepository.save(application));
    }

    @Override
    public ApplicationDTO updateApplication(ApplicationDTO applicationDTO, Integer id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            applicationRepository.save(toEntity(applicationDTO));
            return applicationDTO;
        }
        throw new ApplicationNotFound();
    }

    @Override
    public void deleteApplication(Integer id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            applicationRepository.deleteById(id);
        } else {
            throw new ApplicationNotFound();
        }
    }

    @Override
    public ApplicationDTO toDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        if(application.getJobId() != null)
            applicationDTO.setJobId(application.getJobId());

//        if(application.getService() != null)
//            applicationDTO.setServiceId(application.getService().getId());
        applicationDTO.setWorkerId(application.getWorkerId());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setDateApply(application.getApplyDate());
        return applicationDTO;
    }

    @Override
    public Application toEntity(ApplicationDTO applicationDTO) {
        Application application = new Application();
//        if (applicationDTO.getId() > 0)
//            application.setId(applicationDTO.getId());
        application.setJobId(applicationDTO.getJobId());
        application.setWorkerId(applicationDTO.getWorkerId());
        application.setStatus(applicationDTO.getStatus());
//        application.setService(serviceService.getServiceEntityById(applicationDTO.getServiceId()));
        application.setApplyDate(applicationDTO.getDateApply());
        return application;
    }
}