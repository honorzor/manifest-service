package ru.honorzor.manifestservice.dto;

import lombok.*;
import ru.honorzor.manifestservice.enums.CellState;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CellDTO {
    private Long id;

    private Long count;

    private Long code;

    private CellState cellState;
}
