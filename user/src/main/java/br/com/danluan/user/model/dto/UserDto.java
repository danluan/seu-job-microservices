package br.com.danluan.user.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {
    public UserDto() {};

    private Integer id;

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Login can not be empty")
    private String login;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotEmpty(message = "Phone can not be empty")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "Invalid phone format")
    private String phone;

//    private List<String> roles;

    public UserDto(String name, String email, String login, String password, String phone) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}