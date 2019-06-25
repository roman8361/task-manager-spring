package ru.kravchenko.spring.api;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.kravchenko.spring.exception.AuthenticationException;

import javax.servlet.http.HttpSession;

/**
 * @author Roman Kravchenko
 */

@Service
public interface ISessionService {

    void validateSession(@Nullable final HttpSession session) throws AuthenticationException;

}
