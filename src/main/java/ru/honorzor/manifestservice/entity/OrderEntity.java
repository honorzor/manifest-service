package ru.honorzor.manifestservice.entity;

import lombok.*;
import ru.honorzor.manifestservice.enums.OrderState;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_id_Sequence")
    @SequenceGenerator(name = "order_id_Sequence",
            sequenceName = "SEQ_ORDER_ID", allocationSize = 1)
    private Long id;

    @OneToMany(
            mappedBy = "orderEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @Column(name = "products")
    @ToString.Exclude
    public List<ProductEntity> products;

    @Column(name = "order_state")
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private OrderState orderState = OrderState.FREE;

    @Column(name = "created_time")
    @Builder.Default
    private ZonedDateTime zonedDateTime = ZonedDateTime.now();
}
