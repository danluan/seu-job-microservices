package br.com.danluan.user.resource;

import br.com.danluan.user.model.dto.CredenciaisDto;
import br.com.danluan.user.model.dto.UserDTO;
import br.com.danluan.user.service.impl.UserServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/")
public class UserResource {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserDTOById(id);
    }

    @PostMapping("/getUser")
    public UserDTO getUserById(@RequestBody CredenciaisDto credenciais) {
        return userService.getUserByLogin(credenciais.getLogin());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO salvar( @RequestBody @Valid UserDTO UserDto ){
        return userService.save(UserDto);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@RequestBody @Valid UserDTO UserDto, @PathVariable Integer id) {
        UserDto.setId(id);
        return userService.update(UserDto);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
