package ru.kravchenko.spring.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractEntity {

    @Nullable
    private String id = UUID.randomUUID().toString();

}
