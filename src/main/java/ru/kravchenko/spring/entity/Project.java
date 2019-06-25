package ru.kravchenko.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_Project")
public class Project extends AbstractEntity {

    @Nullable
    @Column(name = "name")
    private String name = "";

    @Nullable
    @Column(name = "description")
    private String description = "";

    @Nullable
    @Column(name = "dateBegin")
    protected Date dateBegin = new Date();

    @Nullable
    @Column(name = "dateEnd")
    protected Date dateEnd = null;

    @NotNull
    @Column(name = "status")
    protected Status status = Status.PLANNED;

    @ManyToOne
    private User user;

    public Project(@Nullable final String name) { this.name = name; }

}
