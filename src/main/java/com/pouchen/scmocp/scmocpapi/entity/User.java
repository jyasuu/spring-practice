package com.pouchen.scmocp.scmocpapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    public String name;

    @Column(insertable = false, columnDefinition = "int default 1")
    Integer gender = 1;

    @Column
    public String password;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private Set<Todo> todos;
}