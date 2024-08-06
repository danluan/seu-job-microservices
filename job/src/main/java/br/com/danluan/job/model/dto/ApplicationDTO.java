package br.com.danluan.job.model.dto;

import br.com.danluan.job.enums.ApplicationStatus;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    Integer id;
    ApplicationStatus status;
    Integer jobId;
    Integer serviceId;
    Integer workerId;
    Date dateApply;
}
