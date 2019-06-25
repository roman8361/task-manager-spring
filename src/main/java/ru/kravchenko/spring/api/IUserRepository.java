package ru.kravchenko.spring.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.kravchenko.spring.entity.User;

import java.util.Collection;

/**
 * @author Roman Kravchenko
 */

@Repository
public interface IUserRepository {

    @NotNull
    Collection<User> findAll();

    @Nullable
    User findById(@Nullable String id);

    @Nullable
    User findByLogin(@Nullable String login);

    @Nullable
    User merge(@Nullable User user);

    void removeById(@Nullable String id);

    void removeAll();

    void init();

    boolean loginExist(@Nullable String login);

}
