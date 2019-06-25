package ru.kravchenko.spring.exception;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman Kravchenko
 */
public class AuthenticationException extends Exception {

    public AuthenticationException(
            @NotNull final String message) { super(message); }

}
