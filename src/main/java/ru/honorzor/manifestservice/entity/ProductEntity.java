package ru.honorzor.manifestservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUCT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_id_Sequence")
    @SequenceGenerator(name = "product_id_Sequence",
            sequenceName = "SEQ_PRODUCT_ID", allocationSize = 1)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private Long code;

    private Long count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}
