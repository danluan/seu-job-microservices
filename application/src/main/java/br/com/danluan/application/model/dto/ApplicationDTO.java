package br.com.danluan.application.model.dto;

import br.com.danluan.application.enums.ApplicationStatus;
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
//    Integer serviceId;
    Integer workerId;
    Date dateApply;
}
