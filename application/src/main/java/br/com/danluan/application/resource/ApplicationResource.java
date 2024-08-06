package br.com.danluan.application.resource;

import br.com.danluan.application.exceptions.ApplicationNotFound;
import br.com.danluan.application.model.dto.ApplicationCreateDTO;
import br.com.danluan.application.model.dto.ApplicationDTO;
import br.com.danluan.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApplicationResource {
    @Autowired
    @Qualifier("applicationServiceImpl")
    private final ApplicationService applicationService;

    @PostMapping
    public ApplicationDTO createApplication(@RequestBody ApplicationCreateDTO applicationDTO) {
        return applicationService.createApplication(applicationDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Integer id) {
        try {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok().build();
        } catch (ApplicationNotFound e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("{id}")
    public ApplicationDTO updateApplication(@RequestBody ApplicationDTO applicationDTO, @PathVariable Integer id) {
       return applicationService.updateApplication(applicationDTO, id);
    }

    @GetMapping
    public List<ApplicationDTO> getAllApplications() { return applicationService.getAllApplications(); }

    @GetMapping("{id}")
    public ApplicationDTO getApplication(@PathVariable Integer id) {
        return applicationService.getApplicationById(id);
    }

    @GetMapping("worker/{id}")
    public List<ApplicationDTO> getApplicationsByWorker(@PathVariable Integer id) {
        return applicationService.getAllApplicationsByWorkerId(id);
    }
}
