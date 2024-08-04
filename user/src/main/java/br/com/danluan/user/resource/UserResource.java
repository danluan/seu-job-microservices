package br.com.danluan.user.resource;

import br.com.danluan.user.model.dto.CredenciaisDto;
import br.com.danluan.user.model.dto.UserDto;
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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserDtoById(id);
    }

    @PostMapping("/getUser")
    public UserDto getUserById(@RequestBody CredenciaisDto credenciais) {
        return userService.getUserByLogin(credenciais.getLogin());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto salvar( @RequestBody @Valid UserDto UserDto ){
        return userService.save(UserDto);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@RequestBody @Valid UserDto UserDto, @PathVariable Integer id) {
        UserDto.setId(id);
        return userService.update(UserDto);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
