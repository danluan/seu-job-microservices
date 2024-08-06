package br.com.danluan.job.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private Integer id;
    private String cnpj;
    private String name;
    private String email;
    private String phone;
}
