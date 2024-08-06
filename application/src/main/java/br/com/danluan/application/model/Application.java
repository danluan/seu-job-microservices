package br.com.danluan.application.model;

import br.com.danluan.application.enums.ApplicationStatus;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "tb_application")
@Setter
@Getter
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer jobId;
    private Integer workerId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_id")
//    private Job job;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_id")
//    private Service service;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "worker_id")
//    private Worker worker;

    @Column()
    private ApplicationStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_date", updatable = false)
    private Date applyDate;

}

