package br.com.danluan.application.feignclients;

import br.com.danluan.application.entity.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user", path="/worker")
public interface WorkerFeignClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Worker> getWorkerById(@PathVariable("id") Integer id);
}
