package br.com.danluan.user.entity.dto;


public class CredenciaisDto {
    public CredenciaisDto() {}

    public CredenciaisDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}