package br.com.danluan.application.model.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationCreateDTO {
    private Integer jobId;
//    private Integer serviceId;
    private Integer workerId;
}
