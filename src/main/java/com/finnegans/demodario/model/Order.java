package com.finnegans.demodario.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 5,
            initialValue = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;

    private String description;
    private Date dateCreated;


    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Customer customer;


    @OneToMany(
            //mappedBy = "order", //Agregue este mappedBy para que no cree la tabla intermedia esa horrible que se crea cuando hay una relación 1:N (Si pongo el mappedBy no me anda bien porque estoy tegistrando la relación con el arreglo y no con el valor individual, voy a dejar la tabla fea por ahora)
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    Set<OrderDetail> orderDetails = new HashSet<>();


}
