package br.com.danluan.application.feignclients;

import br.com.danluan.application.entity.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "job", url="http://localhost:8765/job")
public interface JobFeignClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Job> getJobById(@PathVariable("id") Integer id);
}
