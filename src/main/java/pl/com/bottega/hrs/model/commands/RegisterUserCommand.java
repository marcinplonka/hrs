package pl.com.bottega.hrs.model.commands;

public class RegisterUserCommand implements Command {
    private String login, password, repetedPassword;
    private Integer minPassLength = 6;

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

    public String getRepetedPassword() {
        return repetedPassword;
    }

    public void setRepetedPassword(String repetedPassword) {
        this.repetedPassword = repetedPassword;
    }

    public void validate(ValidationErrors errors) {
        if (!login.matches("[a-zA-Z0-9]*"))
            errors.add("login", "login may contain only letters and digits");
        if (password.length() < minPassLength)
            errors.add("password", "password should contain at least six characters");
        if (!password.equals(repetedPassword))
            errors.add("password", "passwords are not equal");
    }
}
