package pl.com.bottega.hrs.infrastructure;

import pl.com.bottega.hrs.application.users.User;
import pl.com.bottega.hrs.model.repositories.UserRepository;

import javax.persistence.EntityManager;

public class JPAUserRepository implements UserRepository {

    private EntityManager entityManager;

    public JPAUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getByLogin(String login) {
        User user = entityManager.find(User.class, login);
        if(user == null)
            throw new NoSuchEntityException();
        return user;
    }

    @Override
    public void save(User user) {
            entityManager.persist(user);
    }

    public boolean loginFree(String login) {
        return getByLogin(login) == null;
    }


}
