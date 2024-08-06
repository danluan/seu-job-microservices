package br.com.danluan.auth.feignClients;

import br.com.danluan.auth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserServiceClient {

    @GetMapping("/user/{username}")
    User getUserByUsername(@PathVariable String username);
}