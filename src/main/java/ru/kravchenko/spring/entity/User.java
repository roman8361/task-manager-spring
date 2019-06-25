package ru.kravchenko.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_User")
public class User extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Nullable
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @Nullable
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}
