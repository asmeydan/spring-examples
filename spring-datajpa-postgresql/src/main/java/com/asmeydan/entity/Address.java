package com.asmeydan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Address {

    @Id
    @SequenceGenerator(name = "seq_user_address", allocationSize = 1)
    @GeneratedValue( generator = "seq_user_address", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column( length = 500)
    String address;

    @Enumerated
    AddressType addressType;

    Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_address_id")
    private User user;

    public enum AddressType {
        HOME_ADDRESS,
        WORK_ADDRRESS,
        OTHER
    }
}
