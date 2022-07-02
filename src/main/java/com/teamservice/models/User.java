package com.teamservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Long telegramId;

    private String firstName;

    private String lastName;

    private LocalDateTime lastModified;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
