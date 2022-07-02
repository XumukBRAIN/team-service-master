package com.teamservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //todo would be better to make it a constant list
    private String color;

    @OneToOne
    private User teamLead;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "group")
    private List<User> users;


    public Group addUser(User user){
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
        return this;
    }
}
