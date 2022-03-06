package ru.honorzor.manifestservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_CELL")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CellEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cell_id_Sequence")
    @SequenceGenerator(name = "cell_id_Sequence",
            sequenceName = "SEQ_CELL_ID", allocationSize = 1)
    @Column(name = "cell_id")
    private Long id;

    private Long code;

    private Long count;

}
