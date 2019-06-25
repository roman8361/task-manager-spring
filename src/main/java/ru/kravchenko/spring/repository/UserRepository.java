package ru.kravchenko.spring.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kravchenko.spring.api.IUserRepository;
import ru.kravchenko.spring.entity.User;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roman Kravchenko
 */

@Repository
public class UserRepository implements IUserRepository {

    @NotNull
    private Map<String, User> users = new LinkedHashMap<String, User>();

    @PostConstruct
    public void init() {
        final User user = new User();
        user.setLogin("11");
        user.setPassword("11");
        users.put(user.getId(), user);
    }

    @NotNull
    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Nullable
    @Override
    public User findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return users.get(id);
    }

    @Nullable
    @Override
    public User findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) return null;
        for (User user : findAll()) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public boolean loginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        if (findByLogin(login) == null) return false;
        return true;
    }

    @Nullable
    @Override
    public User merge(@Nullable final User user) {
        if (user == null) return null;
        @Nullable final String id = user.getId();
        if (id == null || id.isEmpty()) return null;
        users.put(id, user);
        return user;
    }

    @Override
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        if (!users.containsKey(id)) return;
        users.remove(id);
    }

    @Override
    public void removeAll() {
        users.clear();
    }

}
