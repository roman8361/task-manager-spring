package ru.kravchenko.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_Task")
public class Task extends AbstractEntity {

    @Nullable
    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

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
