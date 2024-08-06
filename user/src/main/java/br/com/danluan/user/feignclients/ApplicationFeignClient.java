package br.com.danluan.user.feignclients;

import br.com.danluan.user.model.dto.ApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "application", url="http://localhost:8765/application/")
public interface ApplicationFeignClient {
    @GetMapping(value = "worker/{id}")
    ResponseEntity<List<ApplicationDTO>> getAllApplicationsByWorkerId(@PathVariable("id") Integer id);
}
