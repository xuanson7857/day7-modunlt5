package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor  // lombok
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String fullName;
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean status = true;
}
