package ru.kravchenko.spring.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kravchenko.spring.api.jpa.UserRepository;
import ru.kravchenko.spring.api.serive.IUserService;
import ru.kravchenko.spring.entity.User;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void persist(@NotNull final User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(@Nullable final String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public @Nullable User findByLogin(@Nullable final String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public @Nullable void merge(@Nullable final User user) {
        userRepository.save(user);
    }

    @Override
    public void removeById(@Nullable final String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        userRepository.deleteAll();
    }

    @Override
    public boolean loginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        final List<String> logins = loginList();
        return logins.contains(login);
    }

    @Override
    public List<String> loginList() {
        return userRepository.loginList();
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
