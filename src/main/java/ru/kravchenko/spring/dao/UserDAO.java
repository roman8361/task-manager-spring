package ru.kravchenko.spring.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kravchenko.spring.api.IUserRepository;
import ru.kravchenko.spring.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Repository
@Transactional
public class UserDAO implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(final User user) { entityManager.persist(user); }


    @Override
    public @NotNull List<User> findAll() {
        List<User> users = entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
        System.out.println(users);
        return users;
    }

    @Override
    public @Nullable User findById(@Nullable final String id) {
        @NotNull final User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public @Nullable User findByLogin(@Nullable final String login) {
        @NotNull final User user = entityManager.createQuery("SELECT e FROM User e WHERE e.login =:login", User.class)
                .setParameter("login", login).getSingleResult();
        return user;
    }


    public void merge(@Nullable final User user) { entityManager.persist(user); }

    @Override
    public void removeById(@Nullable final String id) {
        @NotNull final User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void removeAll() {
        @Nullable final List<User> users = entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
        assert users != null;
        for (final User u : users) entityManager.remove(u);
    }


    @Override
    public boolean loginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        final List<String> logins = loginList();
        return logins.contains(login);
    }

    @Override
    public List<String> loginList() {
        @Nullable final List<String> loginList = entityManager.createQuery("SELECT e.login FROM User e", String.class).getResultList();
        return loginList;
    }

    @Override
    public boolean checkLoginPassword(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if (!loginList().contains(login)) return false;

        final User user = findByLogin(login);
        if (user == null) return false;

        return DigestUtils.md5Hex(password).equals(user.getPasswordHash());
    }

}
