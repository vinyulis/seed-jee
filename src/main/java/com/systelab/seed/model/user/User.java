package com.systelab.seed.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "SeedUser")
@NamedQueries({@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u ORDER BY u.surname DESC"), @NamedQuery(name = User.FIND_BY_LOGIN_PASSWORD, query = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password"),
        @NamedQuery(name = User.ALL_COUNT, query = "SELECT COUNT(u.id) FROM User u")})

@XmlRootElement
public class User {
    public static final String FIND_ALL = "User.findAll";
    public static final String ALL_COUNT = "User.allCount";
    public static final String FIND_BY_LOGIN_PASSWORD = "User.findByLoginAndPassword";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Size(min = 1, max = 255)
    private String surname;

    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 10)
    @Column(length = 10, nullable = false)
    private String login;

    @Size(min = 1, max = 256)
    @Column(length = 256, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "userrole")
    @NotNull
    private UserRole role;

    public User() {
        this.role = UserRole.USER;
    }

    public User(UUID id, String name, String surname, String login, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = UserRole.USER;
    }
}