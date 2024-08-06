package br.com.danluan.job.resource;

import br.com.danluan.job.exception.JobNotFound;
import br.com.danluan.job.feignclient.CompanyFeignClient;
import br.com.danluan.job.model.dto.ApplicationDTO;
import br.com.danluan.job.model.dto.JobDTO;
import br.com.danluan.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class JobResource {
    @Autowired
    @Qualifier("jobServiceImpl")
    private final JobService jobService;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("{id}")
    public JobDTO getJobById(@PathVariable Integer id) {
        return jobService.getJobById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public JobDTO salvar(@RequestBody @Valid JobDTO job) {
        return jobService.save(job);
    }

    @PutMapping("{id}")
    public JobDTO updateJob(@PathVariable Integer id, @RequestBody @Valid JobDTO job) {
        return jobService.updateJob(job, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok().build();
        } catch (JobNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("applications/{id}")
//    public List<ApplicationDTO> getApplications(@PathVariable Integer id) {
//        return jobService.getApplicationsByJob(id);
//    }
}
