package br.com.danluan.auth.resources;

import br.com.danluan.auth.entities.User;
import br.com.danluan.auth.entities.dto.CredenciaisDTO;
import br.com.danluan.auth.exceptions.SenhaInvalidaException;
import br.com.danluan.auth.feignClients.UserServiceClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RefreshScope
@RestController
@RequestMapping(value = "/")
public class AuthResource {

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody CredenciaisDTO credenciais) {
        try {
            System.out.println("Credenciais: " + credenciais.getLogin() + " " + credenciais.getPassword());
            User user = userServiceClient.getUserByUsername(credenciais.getLogin());

            return "Usuário autenticado: " + user.getLogin();
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    
}
