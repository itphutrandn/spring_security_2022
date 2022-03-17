package com.eazybytes.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permissions")
@Setter
@Getter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path_url;

    private String description;

    private Integer enabled;

    @ManyToMany(mappedBy = "permissions")
    List<User> users;
}
