package br.com.danluan.job.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    Integer id;

    @NotNull(message = "Company ID is required.")
    Integer companyId;
    @NotEmpty(message = "Title is required.")
    String title;
    @NotEmpty(message = "Description is required.")
    String description;
    @NotEmpty(message = "Location is required.")
    String location;
    @NotEmpty(message = "Contract type is required.")
    String contract_type;
    @NotEmpty(message = "Salary is required.")
    String salary;
    String publish_date;
}