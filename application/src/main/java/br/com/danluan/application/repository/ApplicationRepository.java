package br.com.danluan.application.repository;

import br.com.danluan.application.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query("SELECT a FROM Application a WHERE a.workerId = :workerId")
    List<Application> findByWorkerId(@Param("workerId") Integer workerId);
}
