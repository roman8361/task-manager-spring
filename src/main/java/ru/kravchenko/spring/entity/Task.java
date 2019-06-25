package ru.kravchenko.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    @Nullable
    private Project project;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    protected Date dateBegin = new Date();

    @Nullable
    protected Date dateEnd = null;

    protected Status status = Status.PLANNED;

    public Task(@Nullable String name) { this.name = name; }

}
