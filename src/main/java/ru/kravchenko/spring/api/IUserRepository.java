package ru.kravchenko.spring.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kravchenko.spring.entity.User;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

public interface IUserRepository {

    void persist(final User user);

    @NotNull
    List<User> findAll();

    @Nullable
    User findById(@Nullable String id);

    @Nullable
    User findByLogin(@Nullable String login);

    @Nullable
    void merge(@Nullable User user);

    void removeById(@Nullable String id);

    void removeAll();

    boolean loginExist(@Nullable String login);

    List<String> loginList();

    boolean checkLoginPassword(@Nullable final String login, @Nullable final String password);

}
