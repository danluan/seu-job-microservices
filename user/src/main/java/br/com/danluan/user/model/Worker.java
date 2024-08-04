package br.com.danluan.user.model;

import br.com.danluan.user.entity.Application;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    private Resume resume;

    public Worker() {
    }

    public Worker(User user) {
        this.user = user;
    }
}