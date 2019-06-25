package ru.kravchenko.spring.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.kravchenko.spring.api.ISessionService;
import ru.kravchenko.spring.constant.FieldConst;
import ru.kravchenko.spring.exception.AuthenticationException;

import javax.servlet.http.HttpSession;

/**
 * @author Roman Kravchenko
 */

@Service
public class SessionService implements ISessionService {

    @Override
    public void validateSession(@Nullable final HttpSession session) throws AuthenticationException {
        if (session.getAttribute(FieldConst.USER) == null) return;
        if (session.getAttribute(FieldConst.USER) == null)
            throw new AuthenticationException("Session is invalid: \nDoes not found logged user! \nPlease re-login!");
    }

}
