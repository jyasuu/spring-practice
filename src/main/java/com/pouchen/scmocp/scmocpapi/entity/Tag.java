package com.pouchen.scmocp.scmocpapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import java.util.Date;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    public String tag;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="tags")
    Set<Todo> todos;
}