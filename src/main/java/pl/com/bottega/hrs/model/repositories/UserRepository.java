package pl.com.bottega.hrs.model.repositories;

import pl.com.bottega.hrs.application.users.User;

public interface UserRepository {

    User getByLogin(String login);
    void save(User user);
    boolean loginFree(String login);
}
