package br.com.danluan.user.service.impl;

import br.com.danluan.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danluan.user.exception.EmailAlreadyInUse;
import br.com.danluan.user.exception.LoginAlreadyInUse;
import br.com.danluan.user.model.dto.UserDTO;
import br.com.danluan.user.model.User;
import br.com.danluan.user.repository.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO save(UserDTO UserDTO) {
        if (!existsLogin(UserDTO.getLogin())) {
            if (!existsEmail(UserDTO.getEmail())) {
                User user = dtoParaUser(UserDTO);
                //user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setPassword(user.getPassword());
                return userParaDTO(userRepository.save(user));
            } else {
                throw new EmailAlreadyInUse();
            }
        } else {
            throw new LoginAlreadyInUse();
        }
    }

    public Boolean existsLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public Boolean existsEmail(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO getUserDTOById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user == null ? null : userParaDTO(user);
    }

    public UserDTO getUserByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado na base de dados."));
        return userParaDTO(user);
    }

    public User dtoParaUser(UserDTO UserDTO){
        User user = new User();
        user.setLogin(UserDTO.getLogin());
        user.setEmail(UserDTO.getEmail());
        user.setName(UserDTO.getName());
        user.setPassword(UserDTO.getPassword());
        user.setPhoneNumber(UserDTO.getPhone());

        return user;
    }

    public UserDTO userParaDTO(User user){
        UserDTO UserDTO = new UserDTO();
        UserDTO.setId(user.getId());
        UserDTO.setLogin(user.getLogin());
        UserDTO.setEmail(user.getEmail());
        UserDTO.setName(user.getName());
        UserDTO.setPassword(user.getPassword());
        UserDTO.setPhone(user.getPhoneNumber());
//        UserDTO.setRoles(user.getRolesByUser());
        return UserDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userParaDTO).collect(Collectors.toList());
    }


    public UserDTO update(UserDTO UserDTO) {
        User user = getUserById(UserDTO.getId());
        user.setName(UserDTO.getName());
        user.setEmail(UserDTO.getEmail());
        user.setLogin(UserDTO.getLogin());
        user.setPassword(UserDTO.getPassword());
        user.setPhoneNumber(UserDTO.getPhone());

//        extractRolesByList(UserDTO, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userParaDTO(userRepository.save(user));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
