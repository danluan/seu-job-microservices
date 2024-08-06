package br.com.danluan.auth.entities;

import lombok.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private String login;

    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    private List<Company> companies = new ArrayList<>();

    private List<Worker> workers = new ArrayList<>();

    private List<Freelancer> freelancers = new ArrayList<>();

    private KafkaProperties.Admin admin;

    public List<String> getRolesByUser(){
        List<String> roles = new ArrayList<>();
        if(!companies.isEmpty()){
            roles.add("COMPANY");
        }
        if(!workers.isEmpty()){
            roles.add("WORKER");
        }
        if(!freelancers.isEmpty()){
            roles.add("FREELANCER");
        }
        if(!(admin == null)){
            roles.add("ADMIN");
        }
        return roles;
    }
}
