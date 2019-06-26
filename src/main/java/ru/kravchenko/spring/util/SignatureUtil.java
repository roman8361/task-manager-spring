package ru.kravchenko.spring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Roman Kravchenko
 */

public final class SignatureUtil {

    @Nullable
    public static String sign(@Nullable final Object value,
                              @Nullable final String salt,
                              @Nullable final Integer cycle) {
        try {
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final String json = objectMapper.writeValueAsString(value);
            return sign(json, salt, cycle);
        } catch (final JsonProcessingException e) {
            return null;
        }
    }

    @Nullable
    public static String sign(@Nullable final String value,
                              @Nullable final String salt,
                              @Nullable final Integer cycle) {
        if (value == null || salt == null || cycle == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < cycle; i++) {
            result = PasswordHashUtil.md5(salt + result + salt);
        }
        return result;
    }

}
