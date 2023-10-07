package com.finnegans.demodario.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @SequenceGenerator(
            name = "order_detail_sequence",
            sequenceName = "order_detail_sequence",
            allocationSize = 5,
            initialValue = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_detail_sequence"
    )
    private Long id;
    private BigDecimal priceSell;
    private Double quantity;

    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "product_id")
    private Product product;
}
