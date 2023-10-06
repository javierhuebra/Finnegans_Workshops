package com.finnegans.demodario.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private BigDecimal price;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<OrderDetail> orderDetails = new HashSet<>();
    //Set es una coleccion que nos permite identificar que no tengamos objetos duplicados
    /*
    No se crea la tabla intermedia products_order_details con mappedBy: Si utilizas la propiedad mappedBy en la anotación @OneToMany,
    puedes evitar la creación de la tabla intermedia. Esto se hace cuando quieres que la entidad "muchos"
    (en este caso, OrderDetail) sea propietaria de la relación y controle cómo se almacenan los datos.

    ejemplo:
    @OneToMany(mappedBy = "product") Esto genera que en la tabla orderDetails aparezca una columna product_id solamente y no cree la otra tabla
    private Set<OrderDetail> orderDetails = new HashSet<>();
    */
}
