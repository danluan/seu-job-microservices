package br.com.danluan.user.service;

import br.com.danluan.user.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(int id);
}
