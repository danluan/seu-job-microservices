package br.com.danluan.job.feignclient;

import br.com.danluan.job.entity.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user", path="/company")
public interface CompanyFeignClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Company> getCompany(@PathVariable("id") Integer id);
}
