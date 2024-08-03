package br.com.danluan.user.service.impl;

import br.com.danluan.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danluan.user.exception.EmailAlreadyInUse;
import br.com.danluan.user.exception.LoginAlreadyInUse;
import br.com.danluan.user.entity.dto.UserDto;
import br.com.danluan.user.entity.User;
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

    @Transactional
    public UserDto save(UserDto UserDto) {
        if (!existsLogin(UserDto.getLogin())) {
            if (!existsEmail(UserDto.getEmail())) {
                User user = dtoParaUser(UserDto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public UserDto getUserDtoById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user == null ? null : userParaDTO(user);
    }

    public UserDto getUserByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado na base de dados."));
        return userParaDTO(user);
    }

    public User dtoParaUser(UserDto UserDto){
        User user = new User();
        user.setLogin(UserDto.getLogin());
        user.setEmail(UserDto.getEmail());
        user.setName(UserDto.getName());
        user.setPassword(UserDto.getPassword());
        user.setPhoneNumber(UserDto.getPhone());

        return user;
    }

    public UserDto userParaDTO(User user){
        UserDto UserDto = new UserDto();
        UserDto.setId(user.getId());
        UserDto.setLogin(user.getLogin());
        UserDto.setEmail(user.getEmail());
        UserDto.setName(user.getName());
        UserDto.setPassword(user.getPassword());
        UserDto.setPhone(user.getPhoneNumber());
//        UserDto.setRoles(user.getRolesByUser());
        return UserDto;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userParaDTO).collect(Collectors.toList());
    }


    public UserDto update(UserDto UserDto) {
        User user = getUserById(UserDto.getId());
        user.setName(UserDto.getName());
        user.setEmail(UserDto.getEmail());
        user.setLogin(UserDto.getLogin());
        user.setPassword(UserDto.getPassword());
        user.setPhoneNumber(UserDto.getPhone());

//        extractRolesByList(UserDto, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userParaDTO(userRepository.save(user));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
