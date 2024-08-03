package br.com.danluan.user.service;

import br.com.danluan.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(int id);
}
