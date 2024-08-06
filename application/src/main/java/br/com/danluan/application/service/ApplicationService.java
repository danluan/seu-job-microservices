package br.com.danluan.application.service;

import br.com.danluan.application.model.Application;
import br.com.danluan.application.model.dto.ApplicationCreateDTO;
import br.com.danluan.application.model.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDTO> getAllApplications();
    List<ApplicationDTO> getAllApplicationsByWorkerId(Integer workerId);
    public ApplicationDTO getApplicationById(Integer id);
    ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO);
    ApplicationDTO updateApplication(ApplicationDTO applicationDTO, Integer id);
    void deleteApplication(Integer id);
    ApplicationDTO toDTO(Application application);
    Application toEntity(ApplicationDTO applicationDTO);
}
