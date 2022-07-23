package com.pouchen.scmocp.scmocpapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper; 
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String task;

    @Column(insertable = false, columnDefinition = "int default 1")
    Integer status;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    Date createTime;

    @LastModifiedDate
    @Column(nullable = false)
    Date updateTime;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="todos_tag", joinColumns = {@JoinColumn(name="tag_id")}, inverseJoinColumns = {@JoinColumn(name="todo_id")})
    Set<Tag> tags;

}