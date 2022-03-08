package ru.honorzor.manifestservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUCT_INFO")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_info_id_Sequence")
    @SequenceGenerator(name = "product_info_id_Sequence",
            sequenceName = "SEQ_PRODUCT_INFO_ID", allocationSize = 1)
    private Long id;

    private Long code;

    private String name;

    private String description;
}
