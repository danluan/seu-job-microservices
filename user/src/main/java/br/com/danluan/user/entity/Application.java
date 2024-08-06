package br.com.danluan.user.entity;

import br.com.danluan.user.enums.ApplicationStatus;
import br.com.danluan.user.model.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private Integer id;
    private Job job;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Service service;

    private Worker worker;

    private ApplicationStatus status;

    private Date applyDate;
}
