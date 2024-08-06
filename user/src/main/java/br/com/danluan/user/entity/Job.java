package br.com.danluan.user.entity;

import br.com.danluan.user.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Integer id;
    private Integer companyId;

//    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Application> applications;

    private String title;

    private String description;

    private String location;

    private String contractType;

    private Float salary;

    private Date publishDate;
}
