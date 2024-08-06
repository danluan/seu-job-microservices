package br.com.danluan.job.entity;

import br.com.danluan.job.model.Job;
import br.com.danluan.job.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
public class Application {

    private Integer id;

    private Job job;

//    private Service service;
//
//    private Worker worker;

    private ApplicationStatus status;

    private Date applyDate;

    public Application() {
    }

    public Application(Job job) {
        this.job = job;
//        this.service = service;
//        this.worker = worker;
    }
}
