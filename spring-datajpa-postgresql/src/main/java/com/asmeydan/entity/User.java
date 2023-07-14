package com.asmeydan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class User {

    @Id
    @SequenceGenerator(name = "seq_user", allocationSize = 1)
    @GeneratedValue( generator = "seq_user", strategy = GenerationType.SEQUENCE)
    private long id;
    @Column( length = 100, name = "name")
    String name;
    @Column( length = 100, name = "surname")
    String surname;

    @OneToMany
    @JoinColumn(name = "user_address_id")
    List<Address> addresses;
}
