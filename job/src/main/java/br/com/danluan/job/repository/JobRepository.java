package br.com.danluan.job.repository;

import br.com.danluan.job.entity.Application;
import br.com.danluan.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(value="SELECT * FROM TB_JOB AS A ORDER BY A.PUBLISH_DATE", nativeQuery = true)
    List<Job> findAll();

    @Query(value="select j from Job j where j.id = :id")
    Optional<Job> findById(Integer id);

//    @Query(value="select a from Application a where a.job.id = :id")
//    List<Application> getApplicationsByJob(Integer id);

}
