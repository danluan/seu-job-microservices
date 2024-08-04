package br.com.danluan.user.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Integer id;

    @NotNull(message = "userId can not be null")
    private Integer userId;

    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)", message = "Invalid CNPJ")
    @NotEmpty(message = "CNPJ can not be empty")
    private String cnpj;

    private String name;
    private String email;
    private String login;
    private String phone;
    private List<JobDTO> jobs;

    public CompanyDTO(Integer userId) {
        this.userId = userId;
    }
}
