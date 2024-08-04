package br.com.danluan.user.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDTO {
    private Integer id;

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)", message = "Invalid CNPJ")
    @NotEmpty(message = "CNPJ can not be empty")
    private String cnpj;

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Login can not be empty")
    private String login;

    @NotEmpty(message = "Phone can not be empty")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "Invalid phone format")
    private String phone;
}
