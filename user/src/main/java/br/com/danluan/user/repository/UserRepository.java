package br.com.danluan.user.repository;

import br.com.danluan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.login = :login")
    Optional<User> findByLogin(String login);

    @Query(value = "select u from User u where u.email = :email")
    Optional<User> findUserByEmail(String email);
}
